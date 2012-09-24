package org.alms.messages;
import org.alms.beans.*;
import javax.ws.rs.core.HttpHeaders;


public interface IMsg 
{
	public void setHeader(HttpHeaders msgHeaders);	
	public void setIncomingMessage(String incomingMessage);	
	public Boolean checkMessageVocubulary();
	public RelatedPary getMsgDestination();
	public RelatedPary getMsgSending();
	public String getMsgId();	
	public String getUserName();
	public String getPassword();
	public String getXSDLocation();
	public String getIncomingMessage();
	
}
