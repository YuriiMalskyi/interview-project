package com.example.demo.exceptions;

public class TransactionSumException extends RuntimeException {

	private static final long serialVersionUID = 8064386303562039052L;

	public TransactionSumException(String message) {
		super(message);
	}
	
}
