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
package org.alms.core;
import java.util.ArrayList;
import java.util.Date;
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
			MessageInfoManager messageController= new MessageInfoManager();		
			UserManager userManager = new UserManager();
			
			List<MessageInfo> msgList=messageController.GetMessages(this.msgData.getMsgSending().getNamespaceID());
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
				messageController.deleteMessage(info.getIncomingMessageId().toString());
			}
			
			msg.setMessageIdList(MsgIdList);		
			msg.setMsgDate(new Date());
			
			PollManager pollMsgManager = new PollManager();
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
