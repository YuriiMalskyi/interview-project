package com.example.demo.enums;

public enum AccountType {

	DOLLAR, EURO, UAH;

	public static AccountType convertString(String possibleType) {
		AccountType accountType;
		
		switch(possibleType) {
		
		case "UAH": accountType = AccountType.UAH;
		break;
		
		case "DOLLAR":  accountType = AccountType.DOLLAR;
		break;
		
		case "EURO": accountType = AccountType.EURO;
		break;
		
		default: accountType = null;
		break;
	}
		return accountType;
	}
}
