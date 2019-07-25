package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.TransactionsHistory;

public interface TransactionsHistoryRepository {

	int count();
	
	void save(TransactionsHistory transactionsHistory);
	
	TransactionsHistory getById(int id);
	
	List<TransactionsHistory> getAllByCardNumber(String cardNumber);
	
	void deleteById(int id);
	
	void deleteTransaction(TransactionsHistory transactionsHistory);
}
