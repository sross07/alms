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
package org.alms.beans;
import java.util.ArrayList;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity
public class UserAccount 
{
	@Id private ObjectId id;	
	private String userName;
	private String password;
	private String AccountOID;
	private String destinationType;
	private String httpVerb;
	private String URL;	
	private String protocol;
	private String fileStore;
	private String fileStorePW;
	private ArrayList<Header> HeaderVariables;
	
	
	public String getFileStore() {
		return fileStore;
	}

	public void setFileStore(String fileStore) {
		this.fileStore = fileStore;
	}

	public String getFileStorePW() {
		return fileStorePW;
	}

	public void setFileStorePW(String fileStorePW) {
		this.fileStorePW = fileStorePW;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public ArrayList<Header> getHeaderVariables() {
		return HeaderVariables;
	}

	public void setHeaderVariables(ArrayList<Header> headerVariables) {
		HeaderVariables = headerVariables;
	}

	public String getDestinationType() {
		return destinationType;
	}

	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}

	public String getHttpVerb() {
		return httpVerb;
	}

	public void setHttpVerb(String httpVerb) {
		this.httpVerb = httpVerb;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAccountOID() {
		return AccountOID;
	}
	
	public void setAccountOID(String accountOid) {
		this.AccountOID = accountOid;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
}
