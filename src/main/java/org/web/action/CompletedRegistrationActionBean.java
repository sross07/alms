package org.web.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class CompletedRegistrationActionBean extends ALMSActionBean 
{
	private String username; 
    private String password;
    private String institutionName;
    private String contactEmail;
    private String oid;
    private String pushUrl;
    private String ConnectionOption;    
    private String pushHeaderVarible;
    private String pushHttpVerb;
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String value) {
		institutionName = value;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getPushUrl() {
		return pushUrl;
	}

	public void setPushUrl(String pushUrl) {
		this.pushUrl = pushUrl;
	}

	public String getConnectionOption() {
		return ConnectionOption;
	}

	public void setConnectionOption(String connectionOption) {
		ConnectionOption = connectionOption;
	}

	public String getPushHeaderVarible() {
		return pushHeaderVarible;
	}

	public void setPushHeaderVarible(String pushHeaderVarible) {
		this.pushHeaderVarible = pushHeaderVarible;
	}

	public String getPushHttpVerb() {
		return pushHttpVerb;
	}

	public void setPushHttpVerb(String pushHttpVerb) {
		this.pushHttpVerb = pushHttpVerb;
	}

    
	@DefaultHandler
	public Resolution defaultHandler()
	{
		System.out.println("Username: " + this.getUsername());
		System.out.println("Password: " + this.getPassword());
		System.out.println("InstitutionName: " + this.getInstitutionName());
		System.out.println("contactEmail: " + this.getContactEmail());
		System.out.println("oid: " + this.getOid());
		System.out.println("Connection Option: " + this.getConnectionOption());
		System.out.println("pushUrl: " + this.getPushUrl());
		System.out.println("PushHeaderVarible: " + this.getPushHeaderVarible());
		System.out.println("PushHttpVerb: " + this.getPushHttpVerb());		
		return new ForwardResolution("/CompletedRegistration.jsp");  
	}	
}
