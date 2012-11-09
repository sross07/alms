package org.alms.core;

import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response.Status.Family;
//import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
//import com.sun.jersey.api.representation.Form;


public class PushController
{	
	public String SendMessage(WebResource r) 
	{
			
		/*ClientResponse response = r.path("test").get(ClientResponse.class);*/		
		ClientResponse response = r.path("SendMessage").header("username", "scott")
				.header("password", "rules")
				.header("SchemaValidation", "NAHLNResultBaseMessage")
				.type(MediaType.APPLICATION_XML)
				.put(ClientResponse.class, "<xml>TEST</xml>");	
				

		return response.getEntity(String.class);
	}
}
