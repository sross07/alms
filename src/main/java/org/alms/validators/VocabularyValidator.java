package org.alms.validators;
import org.alms.messages.IMsg;

public class VocabularyValidator extends MessageDecorator 
{	
	
	private String errorMessage;
	private boolean flag;
	
	public VocabularyValidator(IValidator decoratedValidator,  IMsg messageData) {
		super(decoratedValidator);		
		
		this.errorMessage="";		
		this.flag=messageData.checkMessageVocubulary();
	}

	@Override
	public boolean validate() 
	{
		if (super.validate())
		{
			if (flag)
			{
				return flag;
			}
			else
			{
				this.errorMessage="Coded term (vocubulary) issue in this message";
				return flag;
			}
			
		}
		else
		{
			return false;
		}		
	}
	
	@Override
	public String errorMessage() {
		return super.errorMessage() + " " + this.errorMessage;
	}
}
