package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.TransactionsHistoryDTO;

public interface TransactionsHistoryService {

	void createTransactionHistory(TransactionsHistoryDTO historyDTO);
	
	void editTransactionHistory(TransactionsHistoryDTO historyDTO);

	void deleteTransactionHistory(TransactionsHistoryDTO historyDTO);
	
	void deleteTransactionHistoryById(int id);
	
	TransactionsHistoryDTO getTransactionHistoryById(int id);
	
	List<TransactionsHistoryDTO> getAllTransactionsByCardNumber(String cardNumber);
	
}
