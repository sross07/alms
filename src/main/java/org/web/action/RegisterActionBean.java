package org.web.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.DefaultHandler;

public class RegisterActionBean extends ALMSActionBean 
{	
	@DefaultHandler
	public Resolution defaultHandler()
	{
		return new ForwardResolution("/register.jsp");  
	}
}
