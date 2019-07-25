package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.CreditCardDTO;
import com.example.demo.service.CreditCardService;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService{

	@Override
	public void createCreditCard(CreditCardDTO cardDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editCreditCard(CreditCardDTO cardDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCreditCard(CreditCardDTO cardDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CreditCardDTO getCardByCardNumber(String cardNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByCardNumber(String cardNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAccountBalance(String cardNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void refillMoney(String cardNumber, int summ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMoneyFromTo(String sendingCardnumber, String recievingCartNumber, int summ, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdrawMoney(String cardNumber, int summ) {
		// TODO Auto-generated method stub
		
	}

}
