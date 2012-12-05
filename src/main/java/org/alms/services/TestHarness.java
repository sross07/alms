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
import org.alms.core.PushController;
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

@Path("/TestHarness")
public class TestHarness 
{
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String TestHarnessWorker(String incomingMessage, @Context HttpHeaders headers)
			throws Exception 
	{	
		
		MultivaluedMap<String, String> map = headers.getRequestHeaders();		
		return "I LOVE THIS SHIATE" + map.get("username") + " " + map.get("password")+ " " + map.get("SchemaValidation") + " " + incomingMessage;
	}

}
