package com.example.demo.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CreditCard;
import com.example.demo.entity.TransactionsHistory;
import com.example.demo.enums.Operation;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.repository.TransactionsHistoryRepository;

@Repository
public class JdbcTransactionsHistoryRepository implements TransactionsHistoryRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CreditCardRepository cardRepository;
	
	@Override
	public int count() {
		 return jdbcTemplate
	                .queryForObject("select count(*) from transactions_history", Integer.class);
	}
	
	@Override
	public void save(TransactionsHistory transactionsHistory) {
//		System.out.println("\n\n\n" + transactionsHistory.toString() + "\n\n\n");
		
		jdbcTemplate.update(
				"insert into transactions_history(operation_date, account_from_id, account_to_id, summ, operation, message) "
				+ "values(?,?,?,?,?,?)",
				
				transactionsHistory.getDate(), 
				transactionsHistory.getFrom() == null ? "1" : transactionsHistory.getFrom().getId(), 
				transactionsHistory.getTo() == null ? "1" : transactionsHistory.getTo().getId(),
				transactionsHistory.getSumm(), 
				transactionsHistory.getOperation().toString(), 
				transactionsHistory.getMessage());
	}

	@Override
	public TransactionsHistory getById(int id) {
		return jdbcTemplate.queryForObject("select * from transactions_history where id = " + id, new BeanPropertyRowMapper<TransactionsHistory>(TransactionsHistory.class));
	}

	@Override
	public List<TransactionsHistory> getAllByCardNumber(String cardNumber) {
//		System.out.println("\npublic List<TransactionsHistory> getAllByCardNumber(String cardNumber) BLOCK \n");
		CreditCard card = cardRepository.findByCardNumber(cardNumber);
//		System.out.println("\ncard number - " + cardNumber + " | card id : " + card.getId() + "\n");
//		System.out.println(card.toString() + "\n\n");
                
        return jdbcTemplate.query(
                "select * from transactions_history where account_from_id = ? or account_to_id = ?",
                new Object[]{card.getId(), card.getId()},
                (rs, rowNum) ->
                        new TransactionsHistory(
                        		rs.getInt("id"),
                        		rs.getDate("operation_date"),
                        		cardRepository.findById(rs.getInt("account_from_id")),
                        		cardRepository.findById(rs.getInt("account_to_id")),
                                rs.getBigDecimal("summ"),
                                Operation.convertString(rs.getString("operation")),
                                rs.getString("message")
                        )
        );
	
	}

	@Override
	public void deleteTransactionById(int id) {
		jdbcTemplate.update("delete from transactions_history where id = " + id);
	}

	@Override
	public void deleteTransaction(TransactionsHistory transactionsHistory) {
		jdbcTemplate.update("delete from transactions_history where id = " + transactionsHistory.getId());
	}

	@Override
	public boolean existsById(int id) {
		return jdbcTemplate.queryForObject("select count(*) from transactions_history where  id = " + id, Integer.class) < 1 ? false : true;
	}

}
