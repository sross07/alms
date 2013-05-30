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

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

@Path("/TestHarness")
public class TestHarness 
{
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String TestHarnessWorker(String content, @Context HttpHeaders headers)
			throws Exception 
	{	
		
		MultivaluedMap<String, String> map = headers.getRequestHeaders();		
		return "I LOVE THIS STUFF" + map.get("username") 
				+ " " + map.get("password")+ " " 
				+ map.get("SchemaValidation") + " " 
				+ content;
	}
	
	@GET
	public String TestHarnessWorker()
			throws Exception 
	{		
		return "hi";
	}

}
