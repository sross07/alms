package org.alms.messages;

import java.io.StringReader;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

public class NAHLNResultBaseMessage implements IMsg {
		
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
	public Boolean checkMessageVocubulary() 
	{
		// TO DO:  Check vocubulary here. 
		// Not sure yet which fields need to be check; will do it here
		
		return true;
	}

	@Override
	public org.alms.beans.RelatedPary getMsgDestination() {
		
		org.alms.beans.RelatedPary system= new org.alms.beans.RelatedPary();		
		system.setNamespaceID(GetValue("OPU_R25/MSH/MSH.6/HD.1"));
		system.setUniversialID(GetValue("OPU_R25/MSH/MSH.6/HD.2"));
		system.setUniversialType(GetValue("OPU_R25/MSH/MSH.6/HD.3"));
		
		return system;		
	}
	

	@Override
	public org.alms.beans.RelatedPary getMsgSending() {
		
		org.alms.beans.RelatedPary system= new org.alms.beans.RelatedPary();		
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
}
