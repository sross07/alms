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
import org.alms.DataAccess.*;
import org.alms.beans.*;


@Path("/AckMessage/{uid}")
public class AckMessage 
{
	@GET
	@Produces("text/html")	
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
					
			MessageInfoController manager = new MessageInfoController();			
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
