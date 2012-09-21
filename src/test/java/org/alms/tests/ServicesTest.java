package org.alms.tests;

import static org.junit.Assert.*;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.header.MediaTypes;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import org.junit.Assert;
import org.junit.Test;

public class ServicesTest extends JerseyTest 
{
	 public ServicesTest() throws Exception 
	 {
	        super(new WebAppDescriptor.Builder("org.alms.services")
           .contextPath("ALMS").build());   
     }	
	 
    @Test
    public void testHelloWorld() {
        WebResource webResource = resource();
        String responseMsg = webResource.path("test").get(String.class);
        assertEquals("Hello, World!", responseMsg);
    }

}
