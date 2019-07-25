package com.example.demo.repository.impl;

import java.sql.ResultSet;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CreditCard;
import com.example.demo.entity.TransactionsHistory;
import com.example.demo.enums.Operation;
import com.example.demo.mappers.CreditCardRowMapper;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.repository.TransactionsHistoryRepository;
import com.example.demo.utils.NumberGenerator;

@Repository
public class JdbcCreditCardRepository implements CreditCardRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Lazy
	private TransactionsHistoryRepository historyRepository;
		
	@Autowired
	private CreditCardRowMapper cardRowMapper;
	
	@Autowired
	NumberGenerator numberGenerator;
	
	@Override
	public int count() {
		 return jdbcTemplate
	                .queryForObject("select count(*) from credit_card", Integer.class);
	}
	
	@Override
	public void save(CreditCard creditCard) {
		jdbcTemplate.update(
				"insert into credit_card(account_balance, account_type, card_number, password, client_id) "
				+ "values(?,?,?,?,?)",
				creditCard.getAccountBalance(), creditCard.getAccountType().toString(),
				numberGenerator.generate(), creditCard.getPassword(), creditCard.getClient().getId());
	}

	@Override
	public void update(CreditCard creditCard) {
		jdbcTemplate.update(
                "update credit_card set account_balance = ?, account_type = ?, password = ?, client_id = ? where id = ?",
                creditCard.getAccountBalance(), creditCard.getAccountType().toString(),
                creditCard.getPassword(), creditCard.getClient().getId(), creditCard.getId());
	}

	@Override
	public CreditCard getByCardNumber(String cardNumber) {
		return jdbcTemplate.queryForObject("select * from credit_card where card_number = " + cardNumber, new CreditCardRowMapper());//, new BeanPropertyRowMapper<CreditCard>(CreditCard.class));		
	}
	
	@Override
	public void deleteById(int id) {
		jdbcTemplate.update(
				"delete from credit_card where id = ?", id);
	}

	@Override
	public void deleteByCardNumber(String cardNumber) {
		jdbcTemplate.update(
				"delete from credit_card where card_number = ?", cardNumber);		
	}


	@Override
	public int getAccountBalance(String cardNumber) {
		return jdbcTemplate.queryForObject("select account_balance from credit_card where card_number = " + cardNumber, Integer.class);
	}
	
	@Override
	public void refillMoney(String cardNumber, int summ) {
		
		summ += getAccountBalance(cardNumber);
		jdbcTemplate.update(
                "update credit_card set account_balance = ? where card_number = ?",
                summ, cardNumber);
		
		TransactionsHistory history = new TransactionsHistory(new Date(), null, getByCardNumber(cardNumber), summ, Operation.OPERATION_REFILL, null);
		historyRepository.save(history);
				
	}
 
	@Override
	public void sendMoneyFromTo(String sendingCardnumber, String recievingCartNumber, int summ, String message) {
		jdbcTemplate.update(
                "update credit_card set account_balance = ? where card_number = ?",
                getAccountBalance(sendingCardnumber) - summ, sendingCardnumber);
		jdbcTemplate.update(
                "update credit_card set account_balance = ? where card_number = ?",
                getAccountBalance(recievingCartNumber) + summ, recievingCartNumber);
		
		TransactionsHistory history = 
					new TransactionsHistory(
								new Date(), 
								getByCardNumber(sendingCardnumber), 
								getByCardNumber(recievingCartNumber), 
								summ, 
								Operation.OPERATION_TRANSACTION, 
								message);
		
		historyRepository.save(history);
				
	}

	@Override
	public void withdrawMoney(String cardNumber, int summ) {
		try {
		jdbcTemplate.update(
                "update credit_card set account_balance = ? where card_number = ?",
                getAccountBalance(cardNumber) - summ, cardNumber);
		TransactionsHistory history = new TransactionsHistory(new Date(), null, getByCardNumber(cardNumber), summ, Operation.OPERATION_WITHDRAW, null);
		historyRepository.save(history);
		} finally {
			
		}
	}


}
