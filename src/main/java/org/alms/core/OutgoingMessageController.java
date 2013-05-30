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
import org.alms.messages.*;
import org.alms.DataAccess.*;
import org.alms.beans.*;
import java.util.Date;

public class OutgoingMessageController
{	
	public static void SaveSentMessage(IMsg messageData, String responseMsg) throws Exception
	{
		
		MessageInfo msg = new MessageInfo();
		msg.setIncomingMessage(messageData.getIncomingMessage());
		msg.setOutgoingMessage(responseMsg);
		msg.setDestinationOid(messageData.getMsgDestination().getNamespaceID());
		msg.setIncomingMessageId(messageData.getMsgId());		
		msg.setMsgDate(new Date());
		
		try 
		{
			MessageInfoManager messageManager = new MessageInfoManager();
			messageManager.SaveResultMessage(msg);
		} 
		catch (Exception ex) 
		{
			throw ex; 
		}	
		
	}
}
