package org.alms.messages;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import org.alms.beans.RelatedParty;
import 	org.alms.DataAccess.*;
import 	org.alms.beans.*;

public class MessagePoll implements IMsg {

	@Override
	public String receiverTransmissionType() {
		// TODO Auto-generated method stub
		return "POLL";
	}

	private String userName;
	private String password;
	
	@Override
	public void setHeader(HttpHeaders msgHeaders) {
		MultivaluedMap<String, String> map = msgHeaders.getRequestHeaders();		
		this.userName=map.get("username").toString().replace("[", "").replace("]", "");
		this.password=map.get("password").toString().replace("[", "").replace("]", "");		

	}

	@Override
	public void setIncomingMessage(String incomingMessage) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean checkMessageVocubulary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RelatedParty getMsgDestination() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RelatedParty getMsgSending(){
		
		UserManager userManager;		
		UserAccount User = new UserAccount();
		RelatedParty system = new RelatedParty();
		
		try 
		{
			userManager = new UserManager();
			User=userManager.GetUser(this.getUserName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		system.setNamespaceID(User.getAccountOID());
		return system;
	}

	@Override
	public String getMsgId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getXSDLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIncomingMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
