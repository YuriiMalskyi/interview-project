package com.example.demo.exceptions;

public class AccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6636510019668847266L;

	public AccountNotFoundException(String message) {
		super(message);
	}	
	
}
