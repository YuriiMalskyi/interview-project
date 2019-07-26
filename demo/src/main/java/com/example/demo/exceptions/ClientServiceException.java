package com.example.demo.exceptions;

public class ClientServiceException extends RuntimeException {

	private static final long serialVersionUID = 209507288353319104L;

	public ClientServiceException(String message) {
		super(message);
	}
	
}
