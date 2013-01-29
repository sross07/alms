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
import org.alms.beans.*;
import org.alms.DataAccess.*;

import org.alms.core.protocols.*;


public class PushController
{	
	
	public String SendMessage(IMsg messageData, String schema) throws Exception
	{
				
		String ResponseMessage="";	
		
		IValidator msgValidator = new SimpleValidator();		
		msgValidator = new SecurityValidator(msgValidator, messageData);	
		msgValidator = new SchemaValidator(msgValidator, messageData);
		msgValidator = new ReceiverValidator(msgValidator, messageData);		
		
		if (msgValidator.validate())
		{
			ISendMessage msgController;
			
			UserManager manager = new UserManager();			
			UserAccount destinationAccount = manager.GetUserByUniversialId(messageData.getMsgDestination().getNamespaceID());	 
			
			if (destinationAccount.getProtocol().equals("HTTP"))
			{
				msgController= new SendHTTPMessage();
				
			}
			else
			{
				msgController= new SendHTTPSMessage();
			}
			
			ResponseMessage=msgController.DeliverMessage(messageData, schema);					
			
		}
		else // Message Failed Validation
		{
			AckGenerator AckMessage = new AckGenerator(false, msgValidator.errorMessage(), "CR");		
			ResponseMessage = AckMessage.getHL7AckMessage(messageData);					
		}	
		
		
		//
		//Messages will not be stored during "Push"		
		//org.alms.core.OutgoingMessageController.SaveSentMessage(messageData, ResponseMessage);
		//
		
		return ResponseMessage;				
	}	
	

	
}
