package com.example.demo.repository;

import com.example.demo.entity.CreditCard;

public interface CreditCardRepository {

	int count();
	
	void save(CreditCard creditCard);
	
	void update(CreditCard creditCard);
				
	void deleteById(int id);
	
	CreditCard getByCardNumber(String cardNumber);
	
	void deleteByCardNumber(String cardNumber);
	
	int getAccountBalance(String cardNumber);
	
	void refillMoney(String cardNumber, int summ);
	
	void sendMoneyFromTo(String sendingCardnumber, String recievingCartNumber, int summ, String message);
	
	void withdrawMoney(String cardNumber, int summ);
			
}
