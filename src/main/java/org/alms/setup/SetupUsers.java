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

import org.alms.beans.UserAccount;
import org.alms.core.*;

public class SetupUsers {
	
	public static void main(String[] args) throws Exception {
		System.out.println("Creating Default Accounts ...");	
		
		UserAccount CornellAccount = new UserAccount();
		CornellAccount.setUserName("Cornell");
		CornellAccount.setPassword("CornellRules");
		CornellAccount.setAccountOID("00EN7RT");
			
		UserController userController = new UserController();
		userController.signup(CornellAccount);
		
		System.out.println(" .... Cornell Account Added");
	}

}


/*
 * Default username:
 * 
 * Cornell / CornellRules - 00EN7RT
 * NAHLN / CornellRules - 0034P2K
 * 
 */
