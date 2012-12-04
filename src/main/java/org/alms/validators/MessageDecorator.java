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

abstract public class MessageDecorator implements IValidator {
	protected final IValidator decoratedValidator;
	
	public MessageDecorator(IValidator decoratedValidator) {
		this.decoratedValidator = decoratedValidator;
	}

	public boolean validate() {
		return decoratedValidator.validate();
	}

	public String errorMessage() {
		return decoratedValidator.errorMessage();
	}
}
