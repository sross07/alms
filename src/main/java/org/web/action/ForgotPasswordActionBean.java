package org.web.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;

public class ForgotPasswordActionBean extends ALMSActionBean 
{
	private static String loginView="/UserLogin.action";
	
	@DefaultHandler
	public Resolution defaultHandler()
	{
		return new ForwardResolution("/forgotPassword.jsp");  
	}
	
    public Resolution submit() {
    	return new RedirectResolution(loginView);
    }
}
