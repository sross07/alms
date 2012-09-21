package org.alms.beans;

import java.util.ArrayList;
import org.bson.types.ObjectId;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity
public class PollMessage 
{
	@Id private ObjectId id;
	private String uuid;
	private String outGoingMessage;	
	private ArrayList<String> MessageIdList;
	
	private UserAccount User;	
	
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
