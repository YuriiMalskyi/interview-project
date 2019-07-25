package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.TransactionsHistoryDTO;
import com.example.demo.service.TransactionsHistoryService;

@Service
@Transactional
public class TransactionsHistoryServiceImpl implements TransactionsHistoryService{

	@Override
	public void createTransactionHistory(TransactionsHistoryDTO historyDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editTransactionHistory(TransactionsHistoryDTO historyDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTransactionHistory(TransactionsHistoryDTO historyDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTransactionHistoryById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TransactionsHistoryDTO getTransactionHistoryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionsHistoryDTO> getAllTransactionsByCardNumber(String cardNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
