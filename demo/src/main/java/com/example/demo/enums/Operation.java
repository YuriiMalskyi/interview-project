package com.example.demo.enums;

public enum Operation {

	WITHDRAW, TRANSACTION, REFILL;
	
	public static Operation convertString(String possibleType) {
		Operation operation;
		
		switch(possibleType) {
		
		case "WITHDRAW": operation = Operation.WITHDRAW;
		break;
		
		case "TRANSACTION":  operation = Operation.TRANSACTION;
		break;
		
		case "REFILL": operation = Operation.REFILL;
		break;
		
		default: operation = null;
		break;
	}
		return operation;
	}
}
