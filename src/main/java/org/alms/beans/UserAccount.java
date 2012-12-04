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
	private DestinationInformation userDestinationInfo;	
	
	public DestinationInformation getUserDestinationInfo() {
		return userDestinationInfo;
	}

	public void setUserDestinationInfo(DestinationInformation userDestinationInfo) {
		this.userDestinationInfo = userDestinationInfo;
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
