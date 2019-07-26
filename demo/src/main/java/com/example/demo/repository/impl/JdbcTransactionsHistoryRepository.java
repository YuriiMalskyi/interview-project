package com.example.demo.repository.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
//	@Lazy
	private CreditCardRepository cardRepository;
	
	@Override
	public int count() {
		 return jdbcTemplate
	                .queryForObject("select count(*) from transactions_history", Integer.class);
	}
	
	@Override
	public void save(TransactionsHistory transactionsHistory) {
		System.out.println("\n\n\n" + transactionsHistory.toString() + "\n\n\n");
//		System.out.println("\n\n\n transactionsHistory.getFrom() == null =>" + transactionsHistory.getFrom()==null ? true : false 
//				+ "\ntransactionsHistory.getTo().toString()" + transactionsHistory.getTo()==null ? true : false + "\n\n");
		jdbcTemplate.update(
				"insert into transactions_history(operation_date, account_from_id, account_to_id, summ, operation, message) "
				+ "values(?,?,?,?,?,?)",
//				"insert into transactions_history(operation_date, summ, operation, message) "
//				+ "values(?,?,?,?)",
				transactionsHistory.getDate(), 
				transactionsHistory.getFrom() == null ? "1" : transactionsHistory.getFrom().getId(), 
				transactionsHistory.getTo() == null ? "1" : transactionsHistory.getTo().getId(),
//				transactionsHistory.getFrom().getId() == 0 ? "0" : transactionsHistory.getFrom().getId(), 
//				transactionsHistory.getTo().getId() == 0 ? "0" : transactionsHistory.getTo().getId(),
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
		System.out.println("\npublic List<TransactionsHistory> getAllByCardNumber(String cardNumber) BLOCK \n");
		CreditCard card = cardRepository.findByCardNumber(cardNumber);
		System.out.println("\ncard number - " + cardNumber + " | card id : " + card.getId() + "\n");
		System.out.println(card.toString() + "\n\n");
		
//		return jdbcTemplate.queryForList("select * from transactions_history where account_from_id = " + card.getId() + " or account_to_id = " + card.getId(), new BeanPropertyRowMapper<TransactionsHistory>(TransactionsHistory.class));
	
//		List<TransactionsHistory> historyList = new LinkedList<TransactionsHistory>();
//        List<Map<String, Object>> rows = jdbcTemplate.queryForList("select * from transactions_history where account_from_id = ? or account_to_id = ?", new Object[]{card.getId(), card.getId()});

//        for (Map<String, Object> row : rows) {
//        	TransactionsHistory history = new TransactionsHistory();
//
//        	history.setId(Integer.parseInt(String.valueOf(row.get("ID"))));
//        	history.setDate((Date)row.get("operation_date"));
//        	history.setFrom(cardRepository.findById(Integer.parseInt(String.valueOf(row.get("account_from_id")))));
//        	history.setTo(cardRepository.findById(Integer.parseInt(String.valueOf(row.get("account_to_id")))));
////        	history.setSumm(BigDecimal.valueOf(Double.parseDouble(row.get("summ").toString())));
//        	history.setSumm(new BigDecimal(10.00));
//        	history.setOperation(Operation.convertString((String)row.get("operation")));
//        	history.setMessage((String)row.get("message"));
//        	System.out.println("\n----------------------\n" + history.toString() + "\n----------------------\n");
//        	historyList.add(history);        	
//        }     
//		return historyList;
//        return historyList;
                
        return jdbcTemplate.query(
                "select * from transactions_history where account_from_id = ? or account_to_id = ?",
                new Object[]{card.getId(), card.getId()},
                (rs, rowNum) ->
                        new TransactionsHistory(
                        		rs.getInt("id"),
//                        		LocalDateTime.parse(rs.getString("operation_date")),
//                        		LocalDateTime.ofInstant(rs.getDate("operation_date").toInstant(), ZoneId.systemDefault()),
//                        		LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
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
