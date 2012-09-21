package org.alms.validators;

import org.alms.messages.IMsg;
import java.io.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

public class SchemaValidator extends MessageDecorator  {

	private String errorMessage;
	private String xsdLocation;
	private String inputXML;

	public SchemaValidator(IValidator decoratedValidator,  IMsg messageData)
	{
		super(decoratedValidator);
		this.errorMessage="";
		
		this.xsdLocation=messageData.getXSDLocation();
		this.inputXML=messageData.getIncomingMessage();
		this.inputXML=messageData.getIncomingMessage();
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub	
		
		if (super.validate())
		{
			boolean isValid= true;
			
			try
			{
				SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
				File schemaFile = new File(this.xsdLocation);
				Schema schema = factory.newSchema(schemaFile);
				Validator validator = schema.newValidator();

				// create a source from a string
				Source source = new StreamSource(new StringReader(this.inputXML));

				// check input			
				validator.validate(source);				
			}
			catch(Exception e)
			{
				this.errorMessage = e.toString();
				isValid = false;
			}
			
			return isValid;
		}
		
		else
		{
			return super.validate();
		}	
		 
	}
	
	@Override
	public String errorMessage() {
		return super.errorMessage() + " " + this.errorMessage;
	}
}
