package org.alms.validators;

import org.alms.DataAccess.UserManager;
import org.alms.messages.*;

public class ReceiverValidator extends MessageDecorator 
{

	private String errorMessage;
	private String receiverId;
	
	public ReceiverValidator(IValidator decoratedValidator,  IMsg messageData)
	{	
		super(decoratedValidator);	
		this.errorMessage="";
		this.receiverId=messageData.getMsgDestination().getNamespaceID();
	}
	
	public boolean validate() {
		
		if (super.validate())
		{
			try 
			{
				UserManager userManager = new UserManager();			
				if(userManager.DoesUserExist(this.receiverId))
				{
					return true;
				}
				else
				{
					this.errorMessage= "User account does not have a valid Receiver ID";
					return false;
				}
			} 		
			
			catch (Exception e) 
			{							
				this.errorMessage= e.toString();
				return false;
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
