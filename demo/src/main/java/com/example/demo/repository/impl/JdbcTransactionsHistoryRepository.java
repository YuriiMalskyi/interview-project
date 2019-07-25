package com.example.demo.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CreditCard;
import com.example.demo.entity.TransactionsHistory;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.repository.TransactionsHistoryRepository;

@Repository
public class JdbcTransactionsHistoryRepository implements TransactionsHistoryRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Lazy
	private CreditCardRepository cardRepository;
	
	@Override
	public int count() {
		 return jdbcTemplate
	                .queryForObject("select count(*) from transactions_history", Integer.class);
	}
	
	@Override
	public void save(TransactionsHistory transactionsHistory) {
		jdbcTemplate.update(
				"insert into transactions_history(operation_date, account_from_id, account_to_id, summ, operation, message) "
				+ "values(?,?,?,?,?,?)",
				transactionsHistory.getDate(), transactionsHistory.getFrom().getId(), transactionsHistory.getTo().getId(),
				transactionsHistory.getSumm(), transactionsHistory.getOperation().toString(), transactionsHistory.getMessage());
	}

	@Override
	public TransactionsHistory getById(int id) {
		return jdbcTemplate.queryForObject("select * from transactions_history where id = " + id, new BeanPropertyRowMapper<TransactionsHistory>(TransactionsHistory.class));
	}

	@Override
	public List<TransactionsHistory> getAllByCardNumber(String cardNumber) {
		CreditCard card = cardRepository.findByCardNumber(cardNumber);
		return jdbcTemplate.query("select * from transactions_history where account_from_id = " + card.getId() + " or account_to_id = " + card.getId(), new BeanPropertyRowMapper<TransactionsHistory>(TransactionsHistory.class));
	}

	@Override
	public void deleteTransactionById(int id) {
		jdbcTemplate.update("delete from transactions_history where id = " + id);
	}

	@Override
	public void deleteTransaction(TransactionsHistory transactionsHistory) {
		jdbcTemplate.update("delete from transactions_history where id = " + transactionsHistory.getId());
	}

}
