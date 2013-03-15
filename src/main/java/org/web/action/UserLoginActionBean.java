package org.web.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.DefaultHandler;

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
		if (false)		
		{			// User exists
			return new ForwardResolution(PortalPage);  
		}
		else
		{
			/* User does not exist*/
			System.out.println("Validating user.." + this.username );
			return new RedirectResolution(loginView);
	        
		}
	}
	
	public Resolution reset()
	{	
		System.out.println("Reset..." + this.username);
		return new RedirectResolution(loginView);  		
	}
}
