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
import org.bson.types.ObjectId;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import java.util.Date;

@Entity
public class PollMessage 
{
	@Id private ObjectId id;
	private String uuid;
	private String outGoingMessage;	
	private ArrayList<String> MessageIdList;
	
	private UserAccount User;	
	private Date MsgDate;	
	
	public Date getMsgDate() {
		return MsgDate;
	}

	public void setMsgDate(Date msgDate) {
		MsgDate = msgDate;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getOutGoingMessage() {
		return outGoingMessage;
	}
	public void setOutGoingMessage(String outGoingMessage) {
		this.outGoingMessage = outGoingMessage;
	}

	public UserAccount getUser() {
		return User;
	}
	public void setUser(UserAccount user) {
		User = user;
	}
	
	public ArrayList<String> getMessageIdList() {
		
		if (MessageIdList == null)
		{
			MessageIdList=new ArrayList<String>();				
		}				
		
		return MessageIdList;
	}

	public void setMessageIdList(ArrayList<String>  messageIdList) {
		MessageIdList = messageIdList;
	}

}
