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
package org.alms.core;

import org.alms.messages.*;
import org.alms.validators.IValidator;
import org.alms.validators.ReceiverValidator;
import org.alms.validators.SchemaValidator;
import org.alms.validators.SecurityValidator;
import org.alms.validators.SimpleValidator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.alms.beans.*;
import org.alms.DataAccess.*;


public class PushController
{	
	
	private String schemaValidation;
	

	public String SendMessage(IMsg messageData, String schema) throws Exception
	{
		
		this.schemaValidation=schema;
		String ResponseMessage="";	
		
		IValidator msgValidator = new SimpleValidator();		
		msgValidator = new SecurityValidator(msgValidator, messageData);	
		msgValidator = new SchemaValidator(msgValidator, messageData);
		msgValidator = new ReceiverValidator(msgValidator, messageData);		
		
		if (msgValidator.validate())
		{
			ResponseMessage=DeliverMessageHTTP(messageData);		
		}
		else // Message Failed Validation
		{
			AckGenerator AckMessage = new AckGenerator(msgValidator.validate(), msgValidator.errorMessage());		
			ResponseMessage = AckMessage.getHL7AckMessage(messageData);					
		}	
		
		org.alms.core.OutgoingMessageController.SaveSentMessage(messageData, ResponseMessage);			
		return ResponseMessage;				
	}	
	
	/*** NEED TO REFACTOR THIS ***/
	
	private String DeliverMessageHTTP(IMsg messageData)
	{	
		String username="";
		String password="";
		
		URL serverAddress = null;
		HttpURLConnection  connection = null;
		OutputStreamWriter wr = null;
		BufferedReader rd  = null;
		StringBuilder sb = null;
		String line = null;
	      
		try
		{
			RelatedParty destination = messageData.getMsgDestination();			 
			UserManager manager = new UserManager();
			
			UserAccount destinationAccount = manager.GetUserByUniversialId(destination.getNamespaceID());	
			
			for (Header hd : destinationAccount.getHeaderVariables())
			{
				if (hd.getVariableName().equals("username"))
				{
					username=hd.getValue();
				}
				
				if (hd.getVariableName().equals("password"))
				{
					password=hd.getValue();
				}
			}
			
			serverAddress = new URL(destinationAccount.getURL());
	        connection = null;
	          
	        //Set up the initial connection
	        connection = (HttpURLConnection) serverAddress.openConnection();
	        connection.setRequestMethod(destinationAccount.getHttpVerb());
	        connection.setDoOutput(true);
	        connection.setReadTimeout(10000);       
	     
	        connection.setRequestProperty("username", username);
	        connection.setRequestProperty("password", password);
	        connection.setRequestProperty("SchemaValidation", this.schemaValidation);	        
	        
	        connection.connect();

	        wr = new OutputStreamWriter(connection.getOutputStream());
	        wr.write(messageData.getIncomingMessage());
	        wr.flush();
	        
	        //read the result from the server
	        rd  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        sb = new StringBuilder();
	        
	        while ((line = rd.readLine()) != null)
	        {
	            sb.append(line);
	        }
	        
	        return sb.toString();	
		}
		
		catch(Exception e)
		{
			return e.toString();
		}	
		
	}
}
