package org.alms.setup;

import org.alms.DataAccess.UserController;
import org.alms.beans.UserAccount;

public class SetupUsers {
	
	public static void main(String[] args) throws Exception {
		System.out.println("Creating Default Accounts ...");
		
		UserController accountManager = new UserController();
		
		UserAccount CornellAccount = new UserAccount();
		CornellAccount.setUserName("NAHLN");
		CornellAccount.setPassword("CornellRules");
		CornellAccount.setAccountOID("0034P2K");
		
		accountManager.AddUser(CornellAccount);		
				
		System.out.println(" .... Cornell Account Added");
	}

}
