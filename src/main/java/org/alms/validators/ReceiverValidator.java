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
