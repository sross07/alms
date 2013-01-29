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
import java.util.*;

import org.alms.messages.IMsg;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v26.message.ACK;
import ca.uhn.hl7v2.model.v26.segment.ERR;
import ca.uhn.hl7v2.model.v26.segment.MSA;
import ca.uhn.hl7v2.model.v26.segment.MSH;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.Parser;

public class AckGenerator 
{	
	
	public boolean response;
	public String errorMessage;	
	public String responseCode;
	private java.text.SimpleDateFormat hl7DateFormat;	
	private Long uuid;
	
	public AckGenerator(boolean response, String errorMessage, String errorCode)
	{		
		this.response=response;
		this.errorMessage=errorMessage;
		this.uuid= UUID.randomUUID().getMostSignificantBits();
		this.hl7DateFormat= new java.text.SimpleDateFormat("yyyyMMddHHmmssZ");
		
		if(response)
		{
			this.responseCode=errorCode;
		}
		else
		{
			this.responseCode=errorCode;
		}
	}	
	
	public String HeaderIssue() throws HL7Exception 
	{
		Parser parser = new DefaultXMLParser();
		ACK ackMsg = new ACK();

		MSH mshSegment = ackMsg.getMSH();
		MSA msa = ackMsg.getMSA();

		mshSegment.getFieldSeparator().setValue("NAHLN");
		mshSegment.getEncodingCharacters().setValue("^~\\&amp;");
		// HAPI Seems to only include TZ offset if you set value 
		// to milliseconds 
		//mshSegment.getDateTimeOfMessage().setOffset(-500);
		//mshSegment.getDateTimeOfMessage().setValue(new java.util.Date());
		String timeNow = this.hl7DateFormat.format( new java.util.Date() );
		mshSegment.getDateTimeOfMessage().setValue(timeNow);
		mshSegment.getMessageType().getMessageCode().setValue("ACK");
		mshSegment.getMessageType().getTriggerEvent().setValue("R25");
		mshSegment.getMessageType().getMessageStructure().setValue("ACK_R25");
		mshSegment.getMessageControlID().setValue(this.uuid.toString());

		mshSegment.getMsh4_SendingFacility().getHd1_NamespaceID()
				.setValue("ACK");
		mshSegment.getMsh4_SendingFacility().getHd2_UniversalID()
				.setValue("Z99");
		mshSegment.getMsh4_SendingFacility().getHd3_UniversalIDType()
				.setValue("ACK_Z99");

		mshSegment.getMsh6_ReceivingFacility().getHd1_NamespaceID()
				.setValue("ACK");
		mshSegment.getMsh6_ReceivingFacility().getHd2_UniversalID()
				.setValue("Z99");
		mshSegment.getMsh6_ReceivingFacility().getHd3_UniversalIDType()
				.setValue("ACK_Z99");

		mshSegment.getMsh11_ProcessingID().getPt1_ProcessingID().setValue("P");
		mshSegment.getMsh12_VersionID().getVid1_VersionID().setValue("2.6");
		mshSegment.getMsh21_MessageProfileIdentifier(0).getEi1_EntityIdentifier().setValue("NAHLNACK3.0");

		msa.getMsa2_MessageControlID().setValue("0");
		msa.getAcknowledgmentCode().setValue(this.responseCode);		
		msa.getMsa3_TextMessage().setValue(this.errorMessage);

		if (response==false) {
			ERR err = ackMsg.getERR();
			// Implementing "Code^Text^Coding System" in CE 1-3
			// NOTE: When switched to version 2.6 this becomes Cwe1 - Cwe3
			err.getErrorCodeAndLocation(0).getCodeIdentifyingError()
			        .getCwe1_Identifier().setValue(this.errorMessage);
			err.getErrorCodeAndLocation(0).getCodeIdentifyingError()
			        .getCwe2_Text().setValue("");
			err.getErrorCodeAndLocation(0).getCodeIdentifyingError()
					.getCwe3_NameOfCodingSystem().setValue("HL70357");	
			
		}

		String encodedMessage = parser.encode(ackMsg);
		return encodedMessage;
	
	}
	
	public String getHL7AckMessage(IMsg messageData) throws HL7Exception
	{		
		
		Parser parser = new DefaultXMLParser();
		ACK ackMsg = new ACK();

		MSH mshSegment = ackMsg.getMSH();
		MSA msa = ackMsg.getMSA();

		mshSegment.getFieldSeparator().setValue("NAHLN");
		mshSegment.getEncodingCharacters().setValue("^~\\&amp;");
		// HAPI Seems to only include TZ offset if you set value 
		// to milliseconds 
		//mshSegment.getDateTimeOfMessage().setOffset(-500);
		//mshSegment.getDateTimeOfMessage().setValue(new java.util.Date());
		String timeNow = this.hl7DateFormat.format( new java.util.Date() );
		mshSegment.getDateTimeOfMessage().setValue(timeNow);
		mshSegment.getMessageType().getMessageCode().setValue("ACK");
		mshSegment.getMessageType().getTriggerEvent().setValue("R25");
		mshSegment.getMessageType().getMessageStructure().setValue("ACK_R25");
		mshSegment.getMessageControlID().setValue(this.uuid.toString());

		mshSegment.getMsh4_SendingFacility().getHd1_NamespaceID()
				.setValue(messageData.getMsgSending().getNamespaceID());
		mshSegment.getMsh4_SendingFacility().getHd2_UniversalID()
				.setValue(messageData.getMsgSending().getUniversialID());
		mshSegment.getMsh4_SendingFacility().getHd3_UniversalIDType()
				.setValue(messageData.getMsgSending().getUniversialType());

		mshSegment.getMsh6_ReceivingFacility().getHd1_NamespaceID()
				.setValue(messageData.getMsgDestination().getNamespaceID());
		mshSegment.getMsh6_ReceivingFacility().getHd2_UniversalID()
				.setValue(messageData.getMsgDestination().getUniversialID());
		mshSegment.getMsh6_ReceivingFacility().getHd3_UniversalIDType()
				.setValue(messageData.getMsgSending().getUniversialType());

		mshSegment.getMsh11_ProcessingID().getPt1_ProcessingID().setValue("P");
		mshSegment.getMsh12_VersionID().getVid1_VersionID().setValue("2.6");
		mshSegment.getMsh21_MessageProfileIdentifier(0).getEi1_EntityIdentifier().setValue("NAHLNACK3.0");

		msa.getMsa2_MessageControlID().setValue(messageData.getMsgId());
		msa.getAcknowledgmentCode().setValue(this.responseCode);		
		msa.getMsa3_TextMessage().setValue(this.errorMessage);

		if (response==false) {
			ERR err = ackMsg.getERR();
			// Implementing "Code^Text^Coding System" in CE 1-3
			// NOTE: When switched to version 2.6 this becomes Cwe1 - Cwe3
			err.getErrorCodeAndLocation(0).getCodeIdentifyingError()
			        .getCwe1_Identifier().setValue(this.errorMessage);
			err.getErrorCodeAndLocation(0).getCodeIdentifyingError()
			        .getCwe2_Text().setValue("");
			err.getErrorCodeAndLocation(0).getCodeIdentifyingError()
					.getCwe3_NameOfCodingSystem().setValue("HL70357");	
			
		}

		String encodedMessage = parser.encode(ackMsg);
		return encodedMessage;
		
	}
}
