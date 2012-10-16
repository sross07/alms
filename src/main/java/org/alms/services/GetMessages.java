package org.alms.services;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.alms.core.MessagePollWorker;
import org.alms.messages.*;
import org.alms.validators.*;

@Path("/GetMessages")
public class GetMessages 
{
	
	@GET
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String GetMessageWorker(@Context HttpHeaders headers)
			throws Exception 
	{		
		
		IMsg messageData = new MessagePoll();
		messageData.setHeader(headers);
		messageData.setIncomingMessage("");		
		
		IValidator MsgValidator = new SimpleValidator();		
		MsgValidator= new SecurityValidator(MsgValidator, messageData);
		
		if (MsgValidator.validate())
		{
			MessagePollWorker workerProcess = new MessagePollWorker(messageData);			
			return workerProcess.DoWork();
		}		
		else
		{
			return "user not authenticated";
		}		
		
	}
}