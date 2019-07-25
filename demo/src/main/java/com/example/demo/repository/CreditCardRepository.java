package com.example.demo.repository;

import java.math.BigDecimal;

import com.example.demo.entity.CreditCard;

public interface CreditCardRepository {

	int count();
	
	void save(CreditCard creditCard);
	
	void update(CreditCard creditCard);
				
	void delete(CreditCard creditCard);
	
	void deleteById(int id);
	
	CreditCard findByCardNumber(String cardNumber);
	
	void deleteByCardNumber(String cardNumber);
	
	BigDecimal findBalanceByCardNumber(String cardNumber);
	
	void refillMoney(String cardNumber, BigDecimal summ);
	
	void sendMoneyFromTo(String sendingCardNumber, String recievingCartNumber, BigDecimal summ, String message);
	
	void withdrawMoney(String cardNumber, BigDecimal summ);
			
}
