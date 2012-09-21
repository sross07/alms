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
