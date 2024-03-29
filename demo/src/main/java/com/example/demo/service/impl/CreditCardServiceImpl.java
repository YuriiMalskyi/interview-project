package com.example.demo.service.impl;

import java.math.BigDecimal;

import static com.example.demo.constants.ErrorMessages.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.config.jwt.JWTTokenProvider;
import com.example.demo.domain.ClientDTO;
import com.example.demo.domain.CreditCardDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.CreditCard;
import com.example.demo.exceptions.CreditCardNotFoundException;
import com.example.demo.exceptions.WithdrawSumException;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.service.CreditCardService;
import com.example.demo.utils.ObjectMapperUtils;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService{
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ObjectMapperUtils objectMapperUtils;
	
	@Autowired
	private JWTTokenProvider jwtTokenProvider;
	
	@Autowired 
	private CreditCardRepository cardRepository;
		
	@Override
	public void createCreditCard(CreditCardDTO cardDTO) {
		cardDTO.setPassword(passwordEncoder.encode(cardDTO.getPassword()));
		
		CreditCard card = objectMapperUtils.map(cardDTO, CreditCard.class);
		card.setClient(objectMapperUtils.map(cardDTO.getClientDTO(), Client.class));
		cardRepository.save(card);
	}

	@Override
	public void editCreditCard(CreditCardDTO cardDTO) {
		cardDTO.setPassword(passwordEncoder.encode(cardDTO.getPassword()));
		
		CreditCard card = objectMapperUtils.map(cardDTO, CreditCard.class);
		card.setClient(objectMapperUtils.map(cardDTO.getClientDTO(), Client.class));
		cardRepository.update(card);				
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
		if(cardRepository.existsByCreditCardNumber(cardNumber) == false) {
			throw new CreditCardNotFoundException(CREDIT_CARD_NOT_FOUND_EXCEPTION);
		}
		CreditCard entity_card = cardRepository.findByCardNumber(cardNumber);
		
		CreditCardDTO card = objectMapperUtils.map(entity_card, CreditCardDTO.class);
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
		if(Integer.parseInt(summ.toString()) % 100 != 0 ) throw new WithdrawSumException(ENTERED_SUM_IS_NOT_SUPPORTED_EXCEPTION);
		cardRepository.refillMoney(cardNumber, summ);	
	}

	@Override
	public void sendMoneyFromTo(String sendingCardNumber, String recievingCartNumber, BigDecimal summ, String message) {
		cardRepository.sendMoneyFromTo(sendingCardNumber, recievingCartNumber, summ, message);		
	}

	@Override
	public void withdrawCreditCardBalance(String cardNumber, BigDecimal summ) {
		if(Integer.parseInt(summ.toString()) % 100 != 0 ) throw new WithdrawSumException(ENTERED_SUM_IS_NOT_SUPPORTED_EXCEPTION);
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

	@Override
	public String signin(String username, String password) {
		
		System.out.println(">>> " + username);
		System.out.println(">>> " + password);
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)); // STACK!!!!
		
		return jwtTokenProvider.createToken(username, cardRepository.findByCardNumber(username).getRole());
	}

}
