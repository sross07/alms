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

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.header.MediaTypes;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import org.junit.Assert;
import org.junit.Test;

import org.alms.core.*;

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
      	assertEquals("Hello, World!  Services are working, the paths are: services/AckMessage/{uid} ; serivces/GetMessages; services/SendMessage", responseMsg);

    }
    
    @Test
    public void testPush() {
        //WebResource webResource = resource();
        //String responseMsg = webResource.path("SendMessage").get(String.class);
        //assertEquals("Hello, World!", responseMsg);
    	
        WebResource webResource = resource();
        //String responseMsg = webResource.path("SendMessage").get(String.class);
        
        //webResource.path("test");
    	
    	PushController pusher = new PushController(); 	
    	assertEquals("hi", pusher.SendMessage(webResource));
    	
    }

}
