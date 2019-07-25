package com.example.demo.service;

import com.example.demo.domain.CreditCardDTO;

public interface CreditCardService {
	
	void createCreditCard(CreditCardDTO cardDTO);
	
	void editCreditCard(CreditCardDTO cardDTO);
	
	void deleteCreditCard(CreditCardDTO cardDTO);
	
	CreditCardDTO getCardByCardNumber(String cardNumber);
	
	void deleteByCardNumber(String cardNumber);

	int getAccountBalance(String cardNumber);
	
	void refillMoney(String cardNumber, int summ);
	
	void sendMoneyFromTo(String sendingCardnumber, String recievingCartNumber, int summ, String message);
	
	void withdrawMoney(String cardNumber, int summ);
}
