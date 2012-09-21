package org.alms.DataAccess;

import com.google.code.morphia.Datastore;
import org.alms.beans.*;

import java.util.ArrayList;
import java.util.List;


public class MessageInfoController 
{
	
	private MorphiaMapperSetup morphiaMapper;
	private Datastore ds;


	public MessageInfoController() throws Exception {
		this.morphiaMapper= new MorphiaMapperSetup();		
		this.ds=morphiaMapper.getDs();	
	}
	
	public void SaveResultMessage(MessageInfo msg) 
		throws Exception{
		
		this.ds.save(msg);	
	}

	public MessageInfo GetMessage(String ResultMessageId) 
	{
			
		List<MessageInfo> messageList=
				this.ds.find(org.alms.beans.MessageInfo.class, "incomingMessageId =", ResultMessageId).asList();
		
		return messageList.get(0);			
	}	
	
	public List<MessageInfo> GetMessages(String Receiver)
	{
		List<MessageInfo> messageList=
				this.ds.find(org.alms.beans.MessageInfo.class, "destinationOid =", Receiver).asList();
		
		return messageList;
	}	
	
	public void deleteMessage(String msgId)
	{
		this.ds.findAndDelete(ds.find(org.alms.beans.MessageInfo.class, "incomingMessageId =", msgId));	
	}
	
	public void deleteMessageViaId(ArrayList<String> IdList) 
	{		
		try
		{
			MessageInfoController controller = new MessageInfoController();	
		
			for(String msgId : IdList)
			{
				controller.deleteMessage(msgId);				
			}
		}
		catch(Exception e)
		{
			
		}	
	}
	
}
