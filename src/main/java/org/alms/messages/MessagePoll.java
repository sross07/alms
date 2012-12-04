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
