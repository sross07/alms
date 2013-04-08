package org.web.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class CompletedRegistrationActionBean extends ALMSActionBean 
{
    private String username; 
    public void setUsername(String username) { this.username = username; }    
    public String getUsername() { return username; }
    
	@DefaultHandler
	public Resolution defaultHandler()
	{
		System.out.println("HI - Registration Complete " + this.getUsername());
		return new ForwardResolution("/CompletedRegistration.jsp");  
	}	
}
