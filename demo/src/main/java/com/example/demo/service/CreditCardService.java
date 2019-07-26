package com.example.demo.service;

import java.math.BigDecimal;

import com.example.demo.domain.CreditCardDTO;

public interface CreditCardService {
	
	void createCreditCard(CreditCardDTO cardDTO);
	
	void editCreditCard(CreditCardDTO cardDTO);
	
	void deleteCreditCard(CreditCardDTO cardDTO);
	
	void deleteCreditCardByCardNumber(String cardNumber);
	
	CreditCardDTO getCreditCardByCardNumber(String cardNumber);
	
	BigDecimal getCreditCardBalance(String cardNumber);
	
	void refillCreditCardBalance(String cardNumber, BigDecimal summ);
	
	void sendMoneyFromTo(String sendingCardNumber, String recievingCartNumber, BigDecimal summ, String message);
	
	void withdrawCreditCardBalance(String cardNumber, BigDecimal summ);
	
	boolean existsById(int id);
	
	boolean existsByCreditCardNumber(String cardNumber);
}
