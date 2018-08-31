package com.cg.RestAPIWithJpa.exceptions;

public class Insufficient_Balance_Exception extends Exception {

	private static final long serialVersionUID = 5241189829457363667L;

	public Insufficient_Balance_Exception(String message) {
		super(message);
	}

	
}
