package org.alms.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/test")
public class ALMSTest 
{
	// This is used as a hello world greeting 
    @GET
    public String getGreeting() 
    {
        return "Hello, World!";
    }
}
