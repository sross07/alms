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
import javax.ws.rs.Path;

@Path("/ALMSTest")
public class ALMSTest 
{
	// This is used as a hello world greeting 
    @GET
    public String getGreeting() 
    {
        return "Hello, World!  Services are working, the paths are: services/AckMessage/{uid} ; serivces/GetMessages; services/SendMessage";
    }
}
