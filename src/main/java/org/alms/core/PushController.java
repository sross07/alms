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
		ClientResponse response = r.path("SendMessage").header("username", "Scott")
				.header("password", "CornellRules")
				.header("SchemaValidation", "NAHLNResultBaseMessage")
				.type(MediaType.APPLICATION_XML)
				.put(ClientResponse.class, "<xml>TEST</xml>");	
				

		return response.getEntity(String.class);
	}
	
	public String SendMessage()
	{
		
		
		
		return "Not Implemented";		
	}
}
