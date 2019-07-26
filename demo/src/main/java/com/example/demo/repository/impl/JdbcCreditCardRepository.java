package com.example.demo.repository.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Client;
import com.example.demo.entity.CreditCard;
import com.example.demo.entity.TransactionsHistory;
import com.example.demo.enums.AccountType;
import com.example.demo.enums.Operation;
import com.example.demo.exceptions.CreditCardNotFoundException;
import com.example.demo.mappers.CreditCardRowMapper;
import com.example.demo.repository.ClientRepository;
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
	@Lazy
	private ClientRepository clientRepository;
			
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
	public void delete(CreditCard creditCard) {
		jdbcTemplate.update("delete from credit_card where id = ?", creditCard.getId());
	}
	
	@Override
	public CreditCard findByCardNumber(String cardNumber) {
		CreditCard card = new CreditCard();
		return jdbcTemplate.query("select * from credit_card where card_number = ?", new Object[]{cardNumber}, rs -> {
			
            if(rs.next()){
            	card.setId(rs.getInt("id"));
        		card.setCardNumber(rs.getString("card_number"));
        		card.setPassword(rs.getString("password"));
        		card.setAccountBalance(rs.getBigDecimal("account_balance"));	
        		
        		card.setAccountType(AccountType.convertString(rs.getString("account_type")));
        		card.setClient(clientRepository.getById(rs.getInt("client_id")));
                return card;
            }
            else {
            	return card;
            }

    });
	}
	

	@Override
	public CreditCard findById(int id) {		
		CreditCard card = new CreditCard();
		return jdbcTemplate.query("select * from credit_card where id = ?", new Object[]{id}, rs -> {
			
            if(rs.next()){
            	card.setId(rs.getInt("id"));
        		card.setCardNumber(rs.getString("card_number"));
        		card.setPassword(rs.getString("password"));
        		card.setAccountBalance(rs.getBigDecimal("account_balance"));	
        		
        		card.setAccountType(AccountType.convertString(rs.getString("account_type")));
        		card.setClient(clientRepository.getById(rs.getInt("client_id")));
                return card;
            }
            else {
            	return card;
            }

    });
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
	public BigDecimal findBalanceByCardNumber(String cardNumber) {
		return jdbcTemplate.queryForObject("select account_balance from credit_card where card_number = " + cardNumber, BigDecimal.class);
	}
	
	@Override
	public void refillMoney(String cardNumber, BigDecimal summ) {
		
		summ = summ.add(findBalanceByCardNumber(cardNumber));
		jdbcTemplate.update(
                "update credit_card set account_balance = ? where card_number = ?",
                summ, cardNumber);
		
		TransactionsHistory history = new TransactionsHistory(new Date(), null, findByCardNumber(cardNumber), summ, Operation.REFILL, null);
		historyRepository.save(history);
				
	}
 
	@Override
	public void sendMoneyFromTo(String sendingCardNumber, String recievingCartNumber, BigDecimal summ, String message) {
		jdbcTemplate.update(
                "update credit_card set account_balance = ? where card_number = ?",
                findBalanceByCardNumber(sendingCardNumber).subtract(summ), sendingCardNumber);
		jdbcTemplate.update(
                "update credit_card set account_balance = ? where card_number = ?",
                findBalanceByCardNumber(recievingCartNumber).add(summ), recievingCartNumber);
		
		TransactionsHistory history = 
					new TransactionsHistory(
								new Date(), 
								findByCardNumber(sendingCardNumber), 
								findByCardNumber(recievingCartNumber), 
								summ, 
								Operation.TRANSACTION, 
								message);
		
		historyRepository.save(history);
				
	}

	@Override
	public void withdrawMoney(String cardNumber, BigDecimal summ) {
		try {
		jdbcTemplate.update(
                "update credit_card set account_balance = ? where card_number = ?",
                findBalanceByCardNumber(cardNumber).subtract(summ), cardNumber);
		TransactionsHistory history = new TransactionsHistory(new Date(), null, findByCardNumber(cardNumber), summ, Operation.WITHDRAW, null);
		historyRepository.save(history);
		} finally {
			
		}
	}

	@Override
	public boolean existsById(int id) {
		return jdbcTemplate.queryForObject("select count(*) from credit_card where id = " + id, Integer.class) < 1 ? false : true;
	}

	@Override
	public boolean existsByCreditCardNumber(String cardNumber) {
		return jdbcTemplate.queryForObject("select count(*) from credit_card where card_number = \'" + cardNumber + "\'", Integer.class) < 1 ? false : true;
	}

}
