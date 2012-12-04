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
//DS is it

package org.alms.core;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.alms.DataAccess.UserManager;
import org.alms.beans.UserAccount;

public class UserController
{
	public static final String SALT = "ALMS-ChangingTheWorld";

	public Boolean login(String username, String password) {
		Boolean isAuthenticated = false;

		// remember to use the same SALT value use used while storing password
		// for the first time.
		String saltedPassword = SALT + password;
		String hashedPassword = generateHash(saltedPassword);

		try
		{
			UserManager userManager = new UserManager();
			if (userManager.DoesUserExist(username, hashedPassword))
			{
				isAuthenticated = true;
			}
			else
			{
				isAuthenticated = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAuthenticated;
	}

	public void signup(UserAccount userAccount)
	{
		try
		{
			UserManager userManager = new UserManager();
			String saltedPassword = SALT + userAccount.getPassword();
			String hashedPassword = generateHash(saltedPassword);

			UserAccount CornellAccount = new UserAccount();
			CornellAccount.setUserName(userAccount.getUserName());
			CornellAccount.setPassword(hashedPassword);
			CornellAccount.setAccountOID(userAccount.getAccountOID());

			userManager.AddUser(CornellAccount);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e)
		{
			// handle error here.
		}

		return hash.toString();
	}
}
