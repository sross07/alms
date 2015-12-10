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
