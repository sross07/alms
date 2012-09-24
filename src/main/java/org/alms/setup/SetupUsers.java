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