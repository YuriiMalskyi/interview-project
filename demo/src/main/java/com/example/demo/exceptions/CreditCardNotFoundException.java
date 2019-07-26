package com.example.demo.exceptions;

public class CreditCardNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6636510019668847266L;

	public CreditCardNotFoundException(String message) {
		super(message);
	}	
	
}
