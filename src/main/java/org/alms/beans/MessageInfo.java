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
import java.util.Date;

@Entity
public class MessageInfo 
{
	@Id private ObjectId id;	
	private UserAccount user;
	private String incomingMessageId;
	private String destinationOid;
	private String incomingMessage;
	private String outgoingMessage;
	private Date MsgDate;	
	
	public Date getMsgDate() {
		return MsgDate;
	}

	public void setMsgDate(Date msgDate) {
		MsgDate = msgDate;
	}

	public UserAccount getUser() {
		return user;
	}
	
	public void setUser(UserAccount user) {
		this.user = user;
	}
	
	public String getIncomingMessage() {
		return incomingMessage;
	}
	
	public void setIncomingMessage(String incomingMessage) {
		this.incomingMessage = incomingMessage;
	}
	
	public String getOutgoingMessage() {
		return outgoingMessage;
	}
	
	public void setOutgoingMessage(String outgoingMessage) {
		this.outgoingMessage = outgoingMessage;
	}

	public String getDestinationOid() {
		return destinationOid;
	}

	public void setDestinationOid(String destinationOid) {
		this.destinationOid = destinationOid;
	}

	public String getIncomingMessageId() {
		return incomingMessageId;
	}

	public void setIncomingMessageId(String incomingMessageId) {
		this.incomingMessageId = incomingMessageId;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}	

}
