/*******************************************************************************
 * Copyright (c) 2012 Scott Ross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Scott Ross - initial API and implementation
 ******************************************************************************/
package org.alms.services;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import org.alms.core.AckGenerator;
import org.alms.validators.IValidator;
import org.alms.validators.ReceiverValidator;
import org.alms.validators.SchemaValidator;
import org.alms.validators.SecurityValidator;
import org.alms.validators.SimpleValidator;
import org.alms.validators.VocabularyValidator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.alms.messages.*;
import org.alms.core.*;

@Path("/SendMessage")
public class SendMessage 
{
	
	private String headerError="";
	
	@PUT	
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)	
	public String SendMessageWorker(String incomingMessage, @Context HttpHeaders headers)
			throws Exception 
	{	
		
		if (CheckHeadersError(headers))
		{			
			AckGenerator AckMessage = 
					new AckGenerator(false,this.headerError, "AE");
				
			return AckMessage.HeaderIssue();
		}	
		
		IMsg messageData = doWorkForMessageType(headers, incomingMessage);
		
		if (messageData.receiverTransmissionType().equals("POLL"))
		{
			System.out.println(messageData.receiverTransmissionType());
			
			IValidator msgValidator = new SimpleValidator();		
			msgValidator = new SecurityValidator(msgValidator, messageData);	
			msgValidator = new SchemaValidator(msgValidator, messageData);
			msgValidator = new ReceiverValidator(msgValidator, messageData);
			msgValidator = new VocabularyValidator(msgValidator, messageData);
			
			AckGenerator AckMessage;
			
			if(msgValidator.validate())
			{
				AckMessage = new AckGenerator(true, msgValidator.errorMessage(), "CA");						
			}
			else
			{
				AckMessage = new AckGenerator(true, msgValidator.errorMessage(), "CE");	
			}			
			
			String ResponseMessage = AckMessage.getHL7AckMessage(messageData);			
			org.alms.core.OutgoingMessageController.SaveSentMessage(messageData, ResponseMessage);	
			
			return ResponseMessage;				
		}
		else
		{		
			MultivaluedMap<String, String> map = headers.getRequestHeaders();				
			PushController push=new PushController();	
			return push.SendMessage(messageData, map.get("SchemaValidation").toString().replace("[", "").replace("]", ""));			
		}
	}
	
	private IMsg doWorkForMessageType(HttpHeaders headers, String incomingMessage)
	{
		MultivaluedMap<String, String> map = headers.getRequestHeaders();		
		ApplicationContext context =
				new ClassPathXmlApplicationContext(new String[] {"messageType.xml"});
		
		IMsg MessageData = (IMsg) context.getBean(map.get("SchemaValidation").toString().replace("[", "").replace("]", ""));		
		MessageData.setHeader(headers);
		MessageData.setIncomingMessage(incomingMessage);
		
		return MessageData;
	}
	
	private boolean CheckHeadersError(HttpHeaders headers)
	{
		// false= no errors
		// true= errors
		
		MultivaluedMap<String, String> map = headers.getRequestHeaders();
		
		if (map.containsKey("SchemaValidation") 
				&& map.containsKey("username") 
				&& map.containsKey("password")
				&& SchemaExist( map.get("SchemaValidation").toString().replace("[", "").replace("]", "")))
		{				
			return false;
		}
		else
		{			
			this.headerError= "Specfic Header Values are missing. You need username, password, and SchemaValidation.  Case sensitive.";
			return true;
		}		
	}
	
	private boolean SchemaExist(String schemaName)
	{		
		// Make sure schema Validation is actually in system			
		ApplicationContext context =
				new ClassPathXmlApplicationContext(new String[] {"messageType.xml"});		
			
		String[] beanNames= context.getBeanDefinitionNames();
		
		for(String item : beanNames )
		{
			if (schemaName.equals(item))
			{
				return true;
			}
		}	
		
		return false;
	}
}
