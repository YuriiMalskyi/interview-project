package com.example.demo.exceptions;

public class WithdrawSumException extends RuntimeException {

	private static final long serialVersionUID = -6636510019668847266L;

	public WithdrawSumException(String message) {
		super(message);
	}	
	
}