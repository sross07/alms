package org.alms.messages;
import org.alms.beans.*;
import javax.ws.rs.core.HttpHeaders;


public interface IMsg 
{
	public void setHeader(HttpHeaders msgHeaders);	
	public void setIncomingMessage(String incomingMessage);	
	public Boolean checkMessageVocubulary();
	public RelatedParty getMsgDestination();
	public RelatedParty getMsgSending();
	public String getMsgId();	
	public String getUserName();
	public String getPassword();
	public String getXSDLocation();
	public String getIncomingMessage();
	public String receiverTransmissionType();
	
}
