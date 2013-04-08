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
			if (!flag)
			{
				this.errorMessage="Coded term (vocubulary) issue in this message";
			}	
			
			return flag;
			
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
