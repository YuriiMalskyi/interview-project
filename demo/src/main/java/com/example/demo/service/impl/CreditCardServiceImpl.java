package com.example.demo.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.ClientDTO;
import com.example.demo.domain.CreditCardDTO;
import com.example.demo.entity.CreditCard;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.service.CreditCardService;
import com.example.demo.utils.ObjectMapperUtils;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService{

	@Autowired
	private ObjectMapperUtils objectMapperUtils;
	
	@Autowired 
	private CreditCardRepository cardRepository;
		
	@Override
	public void createCreditCard(CreditCardDTO cardDTO) {
		cardRepository.save(objectMapperUtils.map(cardDTO, CreditCard.class));
	}

	@Override
	public void editCreditCard(CreditCardDTO cardDTO) {
		cardRepository.update(objectMapperUtils.map(cardDTO, CreditCard.class));				
	}

	@Override
	public void deleteCreditCard(CreditCardDTO cardDTO) {
		cardRepository.delete(objectMapperUtils.map(cardDTO, CreditCard.class));	
	}

	@Override
	public void deleteCreditCardByCardNumber(String cardNumber) {
		cardRepository.deleteByCardNumber(cardNumber);
	}

	@Override
	public CreditCardDTO getCreditCardByCardNumber(String cardNumber) {
		CreditCardDTO card = new CreditCardDTO();	
		CreditCard entity_card = cardRepository.findByCardNumber(cardNumber);
		
		card = objectMapperUtils.map(entity_card, CreditCardDTO.class);
		card.setClientDTO(objectMapperUtils.map(entity_card.getClient(), ClientDTO.class));
		
//		return objectMapperUtils.map(cardRepository.findByCardNumber(cardNumber), CreditCardDTO.class);
		return card;
	}

	@Override
	public BigDecimal getCreditCardBalance(String cardNumber) {
		return cardRepository.findBalanceByCardNumber(cardNumber);
	}

	@Override
	public void refillCreditCardBalance(String cardNumber, BigDecimal summ) {
		cardRepository.refillMoney(cardNumber, summ);	
	}

	@Override
	public void sendMoneyFromTo(String sendingCardNumber, String recievingCartNumber, BigDecimal summ, String message) {
		cardRepository.sendMoneyFromTo(sendingCardNumber, recievingCartNumber, summ, message);		
	}

	@Override
	public void withdrawCreditCardBalance(String cardNumber, BigDecimal summ) {
		cardRepository.withdrawMoney(cardNumber, summ);
	}

	@Override
	public boolean existsById(int id) {
		return cardRepository.existsById(id);
	}

	@Override
	public boolean existsByCreditCardNumber(String cardNumber) {
		return cardRepository.existsByCreditCardNumber(cardNumber);
	}

}
