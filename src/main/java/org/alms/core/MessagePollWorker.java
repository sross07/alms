package org.alms.core;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.alms.DataAccess.MessageInfoManager;
import org.alms.beans.MessageInfo;
import org.alms.messages.*;
import org.alms.beans.*;
import org.alms.DataAccess.*;

public class MessagePollWorker
{
	private IMsg msgData;
	private UUID messageId;
	
	public MessagePollWorker(IMsg msgData)
	{
		this.msgData=msgData;
		this.messageId=UUID.randomUUID();			
	}
	
	public String DoWork() throws Exception
	{				
		try 
		{
			PollManager pollMsgManager= new PollManager();
			
			MessageInfoManager messageController;
			List<MessageInfo> msgList;
			UserManager userManager = new UserManager();
			
			messageController = new MessageInfoManager();
			msgList=messageController.GetMessages(this.msgData.getMsgSending().getNamespaceID());
			String xmlResponse=this.CreateXML(msgList, messageId);
			UserAccount user = userManager.GetUser(msgData.getUserName());
			
			PollMessage msg =new PollMessage();
			msg.setUser(user);
			msg.setUuid(messageId.toString());
			msg.setOutGoingMessage(xmlResponse);
			
			ArrayList<String> MsgIdList = new ArrayList<String>();
			
			for(MessageInfo info : msgList)
			{
				MsgIdList.add(info.getIncomingMessageId().toString());				
			}
			
			msg.setMessageIdList(MsgIdList);		
			pollMsgManager.Save(msg);	
						
			return xmlResponse;
			
		} catch (Exception e) {
			throw e;
		}		
	}
	
	private String CreateXML(List<MessageInfo> msgList, UUID messageId)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("<MessagePoll>").append("\n");		
		sb.append("<PollUID>" + messageId.toString() + "</PollUID>").append("\n");		
		sb.append("<OPU_R25_Wrapper>");
		
		for(MessageInfo info : msgList)
		{			
			sb.append(this.EliminateXMLTag(info.getIncomingMessage()));			
		}	
		
		sb.append("</OPU_R25_Wrapper>").append("\n");		
		sb.append("</MessagePoll>");		
		return sb.toString();
	}
	
	private String EliminateXMLTag(String message)
	{
		return message.replace("<?xml version=\"1.0\"?>", "");	
	}	
}
