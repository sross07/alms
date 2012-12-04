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
package org.alms.DataAccess;

import java.util.List;
import org.alms.beans.UserAccount;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.*;

public class UserManager {
	
	private MorphiaMapperSetup morphiaMapper;
	private Datastore ds;	
	
	public UserManager() throws Exception
	{
		this.morphiaMapper = new MorphiaMapperSetup();		
		this.ds = morphiaMapper.getDs();		
	}
	
	public boolean DoesUserExist(String UserName, String Password) 
	{
		Query<UserAccount> q = ds.createQuery(org.alms.beans.UserAccount.class).filter("userName =", UserName).filter("password =", Password);
		List<UserAccount> accountList = q.asList();
		
		if (accountList.size() == 1)
		{			
			return true;
		}
		else
		{		
			return false;
		}
	}
	
	public boolean DoesUserExist(String accountOid)
	{
		
		Query<UserAccount> q = ds.createQuery(org.alms.beans.UserAccount.class).filter("AccountOID =", accountOid);
		List<UserAccount> accountList = q.asList();
		
		if (accountList.size() == 1)
		{			
			return true;
		}
		else
		{		
			return false;
		}
	}
	
	public UserAccount GetUser(String UserName)
	{		
		List<UserAccount> accountList = this.ds.find(org.alms.beans.UserAccount.class, "userName =", UserName).asList();		
		return accountList.get(0);		
	}
	
	public void AddUser(UserAccount account) 
	{		
		this.ds.save(account);
	}
}
