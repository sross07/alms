package org.web.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.DefaultHandler;

import org.alms.core.*;
import org.alms.beans.*;
import org.alms.DataAccess.*;

public class UserLoginActionBean extends ALMSActionBean
{	
	private static String loginView="/UserLogin.action";
	private static String PortalPage="/portal.action";		
   
    private String username;
    private String password;
    
    /** The username of the user trying to log in. */
    public void setUsername(String username) { this.username = username; }

    /** The username of the user trying to log in. */
    public String getUsername() { return username; }

    /** The password of the user trying to log in. */
    public void setPassword(String password) { this.password = password; }

    /** The password of the user trying to log in. */
    public String getPassword() { return password; }
	
	@DefaultHandler
	public Resolution defaultHandler()
	{
		return new ForwardResolution("/login.jsp");  
	}
	
	public Resolution submit()
	{	
		try 
		{	
			UserController manager = new UserController();		
			
			if(manager.login(username, password))
			{
				// Need to add the user to the context				
				UserManager dataManager = new UserManager();					
				getContext().setUser(dataManager.GetUser(username));						
				return new ForwardResolution(PortalPage);  
			}
			else
			{				
				return new RedirectResolution(loginView);				
			}			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new RedirectResolution(loginView);	
		}		
	}
	
	public Resolution reset()
	{
		return new RedirectResolution(loginView);  		
	}
}
