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
