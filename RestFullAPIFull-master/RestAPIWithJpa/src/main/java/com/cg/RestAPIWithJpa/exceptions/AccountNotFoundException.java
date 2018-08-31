package com.cg.RestAPIWithJpa.exceptions;

public class AccountNotFoundException extends Exception {

	private static final long serialVersionUID = 1697593187013769593L;

	public AccountNotFoundException(String message) {
		super(message);
	}

	
}
