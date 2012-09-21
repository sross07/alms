package org.alms.beans;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity
public class MessageInfo 
{
	@Id private ObjectId id;	
	private UserAccount user;
	private String incomingMessageId;
	private String destinationOid;
	private String incomingMessage;
	private String outgoingMessage;
	
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
