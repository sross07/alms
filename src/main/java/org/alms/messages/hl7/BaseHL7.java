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
package org.alms.messages.hl7;

import org.alms.messages.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import org.xml.sax.InputSource;

public abstract class BaseHL7 implements IMsg
{
	private String userName;
	private String password;
	private String XSDLocation;
	private String incomingMessage;
		
	@Override
	public String getIncomingMessage() {
		return incomingMessage;
	}

	@Override
	public void setIncomingMessage(String incomingMessage) {
		this.incomingMessage = incomingMessage;
	}

	@Override
	public String getXSDLocation() {
		return XSDLocation;
	}

	public void setXSDLocation(String xSDLocation) {
		XSDLocation = xSDLocation;
	}

	@Override
	public String getUserName()
	{
		return this.userName;		
	}
	
	@Override
	public String getPassword()
	{
		return this.password;		
	}

	@Override
	public void setHeader(HttpHeaders msgHeaders) 
	{
		MultivaluedMap<String, String> map = msgHeaders.getRequestHeaders();		
		this.userName=map.get("username").toString().replace("[", "").replace("]", "");
		this.password=map.get("password").toString().replace("[", "").replace("]", "");		
	}
	
	@Override
	public org.alms.beans.RelatedParty getMsgDestination() {
		
		org.alms.beans.RelatedParty system= new org.alms.beans.RelatedParty();		
		system.setNamespaceID(GetValue("OPU_R25/MSH/MSH.6/HD.1"));
		system.setUniversialID(GetValue("OPU_R25/MSH/MSH.6/HD.2"));
		system.setUniversialType(GetValue("OPU_R25/MSH/MSH.6/HD.3"));
		
		return system;		
	}
	

	@Override
	public org.alms.beans.RelatedParty getMsgSending() {
		
		org.alms.beans.RelatedParty system= new org.alms.beans.RelatedParty();		
		system.setNamespaceID(GetValue("OPU_R25/MSH/MSH.4/HD.1"));
		system.setUniversialID(GetValue("OPU_R25/MSH/MSH.4/HD.2"));
		system.setUniversialType(GetValue("OPU_R25/MSH/MSH.4/HD.3"));
		
		return system;		
	}

	@Override
	public String getMsgId() {
		return GetValue("OPU_R25/MSH/MSH.10");
	}
	
	private String GetValue(String path) {

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		InputSource source = new InputSource(new StringReader(
				this.incomingMessage));

		String value;
		try {
			value = xpath.evaluate(path, source);
			return value;
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "N/A";
		}
	}
	
	@Override
	public String receiverTransmissionType() {
		// TODO Auto-generated method stub
		return "POLL";
	}
	
	public abstract Boolean checkMessageVocubulary();	
}
