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
package org.alms.DataAccess;

import com.google.code.morphia.Datastore;
import org.alms.beans.*;

import java.util.ArrayList;
import java.util.List;


public class MessageInfoManager 
{
	
	private MorphiaMapperSetup morphiaMapper;
	private Datastore ds;


	public MessageInfoManager() throws Exception {
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
			MessageInfoManager controller = new MessageInfoManager();	
		
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
