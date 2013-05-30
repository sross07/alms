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

import java.util.ArrayList;
import org.alms.messages.IMsg;
import org.alms.messages.MessagePoll;
import org.alms.validators.IValidator;
import org.alms.validators.SecurityValidator;
import org.alms.validators.SimpleValidator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.alms.DataAccess.*;
import org.alms.beans.*;


@Path("/AckMessage/{uid}")
public class AckMessage 
{
	@GET
	@Produces(MediaType.APPLICATION_XML)	
	public String AckMessageHandler(@PathParam("uid") String MsgId, @Context HttpHeaders headers) throws Exception
	{	
		
		IMsg messageData = new MessagePoll();
		messageData.setHeader(headers);
		messageData.setIncomingMessage(MsgId);		
		
		IValidator MsgValidator = new SimpleValidator();		
		MsgValidator= new SecurityValidator(MsgValidator, messageData);
		
		if (MsgValidator.validate())
		{
			PollManager PollManager = new PollManager();
			PollMessage msg= PollManager.GetPollMessage(MsgId);			
			
			ArrayList<String> ListIds= msg.getMessageIdList();						
					
			MessageInfoManager manager = new MessageInfoManager();			
			for(String str : ListIds)
			{
				manager.deleteMessage(str);
			}		
			
			return "Batch Acknowledged";
		}
		else
		{
			return "user not authenticated";
		}
	}
}
