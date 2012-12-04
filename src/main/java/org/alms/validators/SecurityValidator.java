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

import org.alms.core.*;
import org.alms.messages.IMsg;

public class SecurityValidator extends MessageDecorator {
	
	private String userName;
	private String passWord;
	private String errorMessage;
	
	public SecurityValidator(IValidator decoratedValidator, IMsg messageData) 
	{			
		super(decoratedValidator);		
		
		this.userName=messageData.getUserName();
		this.passWord=messageData.getPassword();		
		this.errorMessage="";
	}

	@Override
	public boolean validate() 
	{	
		if (super.validate())
		{
			try 
			{				
				UserController userController = new UserController();				
				if(userController.login(this.userName, this.passWord))
				{
					return true;
				}
				else
				{					
					this.errorMessage= "User account is not valid";
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
	public String errorMessage() 
	{
		return super.errorMessage() + " " + this.errorMessage;
	}
}
