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
package org.alms.setup;

import java.util.ArrayList;

import javax.ws.rs.core.MultivaluedMap;

import org.alms.beans.UserAccount;
import org.alms.core.*;
import org.alms.DataAccess.*;

import com.mongodb.BasicDBObject;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class SetupUsers {
	
	public static void main(String[] args) throws Exception {
		
		/*
		System.out.println("Creating Default Accounts ...");	
		
		UserAccount CornellAccount = new UserAccount();
		CornellAccount.setUserName("Cornell");
		CornellAccount.setPassword("CornellRules");
		CornellAccount.setAccountOID("00EN7RT");
			
		UserController userController = new UserController();
		userController.signup(CornellAccount);
		
		System.out.println(" .... Cornell Account Added");
		*/
		
		
		UserManager manager = new UserManager();
		
		UserAccount CornellAccount= manager.GetUser("NAHLN");
		CornellAccount.setHttpVerb("PUT");
		CornellAccount.setDestinationType("PUSH");	
		
		CornellAccount.setURL("http://localhost:8080/alms/services/TestHarness");
		
		CornellAccount.setProtocol("HTTP");
		
		ArrayList<org.alms.beans.Header> headerList = new ArrayList<org.alms.beans.Header>();		
		
		org.alms.beans.Header userHd = new org.alms.beans.Header();		
		userHd.setVariableName("user");
		userHd.setValue("scott");
		
		org.alms.beans.Header userPw = new org.alms.beans.Header();		
		userPw.setVariableName("password");
		userPw.setValue("abc123");
		
		headerList.add(userHd);
		headerList.add(userPw);
				
		CornellAccount.setHeaderVariables(headerList);		
		manager.AddUser(CornellAccount);		
	}
}


/*
 * Default username:
 * 
 * Cornell / CornellRules - 00EN7RT
 * NAHLN / CornellRules - 0034P2K
 * 
 */
