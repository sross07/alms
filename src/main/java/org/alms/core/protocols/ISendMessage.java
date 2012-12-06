package org.alms.core.protocols;

import org.alms.messages.IMsg;

public interface ISendMessage 
{
	String DeliverMessage(IMsg messageData, String SchemaValidation);
	

}
