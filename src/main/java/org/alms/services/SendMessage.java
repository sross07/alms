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

@Path("/SendMessage")
public class SendMessage 
{
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String SendMessageWorker(String incomingMessage, @Context HttpHeaders headers)
			throws Exception 
	{	
		
		IMsg messageData = doWorkForMessageType(headers, incomingMessage);
		
		IValidator msgValidator = new SimpleValidator();		
		msgValidator= new SecurityValidator(msgValidator, messageData);	
		msgValidator = new SchemaValidator(msgValidator, messageData);
		msgValidator = new ReceiverValidator(msgValidator, messageData);
		msgValidator = new VocabularyValidator(msgValidator, messageData);
		
		AckGenerator AckMessage = new AckGenerator(msgValidator.validate(), msgValidator.errorMessage());		
		String ResponseMessage = AckMessage.getHL7AckMessage(messageData);		
		
		org.alms.core.OutgoingMessageController.SaveSentMessage(messageData, ResponseMessage);	
		
		return ResponseMessage;
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
}
