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
package org.web.beans;

import org.alms.beans.*;
import org.alms.core.*;

public class AccountBean 
{

	private String userName;
	private String passWord;
	private String accountOid;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getAccountOid() {
		return accountOid;
	}

	public void setAccountOid(String accountOid) {
		this.accountOid = accountOid;
	}	
	
	public void processData() {
		
		UserAccount account = new UserAccount();
		account.setUserName(this.getUserName());
		account.setPassword(this.getPassWord());
		account.setAccountOID(this.getAccountOid());
			
		UserController userController = new UserController();
		userController.signup(account);	
	}

	public boolean validateData() {

		// Put more checks to ensure we don't duplicate accounts
		// Put other checks here
		return true;
	}

}
