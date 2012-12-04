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
package org.alms.tests;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.header.MediaTypes;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import org.junit.Assert;
import org.junit.Test;


public class HeaderTest extends JerseyTest
{
	public HeaderTest() throws Exception 
	{
        super(new WebAppDescriptor.Builder("org.alms.services")
        	.contextPath("ALMS").build());   
    }
	
	@Test 
	public void HeadersDoNotExist_true()
	{
		WebResource webResource = resource();
		ClientResponse response = webResource.path("SendMessage").header("username", "Scott")
				.header("password", "CornellRules")
				//.header("SchemaValidation", "NAHLNResultBaseMessage")
				.type(MediaType.APPLICATION_XML)
				.put(ClientResponse.class, "<xml>TEST</xml>");	
				
		assertEquals(response.getEntity(String.class),"AE- Specfic Header Values are missing. You need username, password, and SchemaValidation.  Case sensitive.");			
	}
	
	@Test 
	public void HeaderSchemaDoNotExist_true()
	{
		WebResource webResource = resource();
		ClientResponse response = webResource.path("SendMessage").header("username", "Scott")
				.header("password", "CornellRules")
				.header("SchemaValidation", "abc")
				.type(MediaType.APPLICATION_XML)
				.put(ClientResponse.class, "<xml>TEST</xml>");	
				
		assertEquals(response.getEntity(String.class),"AE- ");			
	}
	
	@Test 
	public void HeaderSchemaDoNotExist_false()
	{
		WebResource webResource = resource();
		ClientResponse response = webResource.path("SendMessage").header("username", "Scott")
				.header("password", "CornellRules")
				.header("SchemaValidation", "NAHLNResultBaseMessage")
				.type(MediaType.APPLICATION_XML)
				.put(ClientResponse.class, "<xml>TEST</xml>");	
		
		assertFalse(response.getEntity(String.class).equals("AE- "));
	}
	
/*	@Test 
	public void HeadersExist_true()
	{
	
	}
	
	@Test 
	public void HeadersDoNotExist_true()
	{
		
	}
	
	@Test
	public void HeadersUserNameNotExist_true()
	{
	
	}

	@Test
	public void HeadersPasswordNotExist_true()
	{
	}
	
	@Test 
	public void HeadersSchemaNotExist_true()
	{	
	
	}
	*/
}
