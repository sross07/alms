package org.alms.DataAccess;

import java.util.List;
import org.alms.beans.UserAccount;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.*;

public class UserController {
	
	private MorphiaMapperSetup morphiaMapper;
	private Datastore ds;	
	
	public UserController() throws Exception
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
