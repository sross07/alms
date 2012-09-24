package org.alms.core;
import org.alms.messages.*;
import org.alms.DataAccess.*;
import org.alms.beans.*;

public class OutgoingMessageController
{	
	public static void SaveSentMessage(IMsg messageData, String responseMsg) throws Exception
	{
		
		MessageInfo msg = new MessageInfo();
		msg.setIncomingMessage(messageData.getIncomingMessage());
		msg.setOutgoingMessage(responseMsg);
		msg.setDestinationOid(messageData.getMsgDestination().getNamespaceID());
		msg.setIncomingMessageId(messageData.getMsgId());
		
		try 
		{
			MessageInfoManager messageManager = new MessageInfoManager();
			messageManager.SaveResultMessage(msg);
		} 
		catch (Exception e) 
		{
			throw e; 
		}	
		
	}
}
