package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.TransactionsHistoryDTO;
import com.example.demo.entity.TransactionsHistory;
import com.example.demo.repository.TransactionsHistoryRepository;
import com.example.demo.service.TransactionsHistoryService;
import com.example.demo.utils.ObjectMapperUtils;

@Service
@Transactional
public class TransactionsHistoryServiceImpl implements TransactionsHistoryService{

	@Autowired
	private TransactionsHistoryRepository historyRepository;
	
	@Autowired
	private ObjectMapperUtils objectMapperUtils;
	
	@Override
	public void createTransactionHistory(TransactionsHistoryDTO historyDTO) {
		historyRepository.save(objectMapperUtils.map(historyDTO, TransactionsHistory.class));
	}

	@Override
	public void deleteTransactionHistory(TransactionsHistoryDTO historyDTO) {
		historyRepository.deleteTransaction(objectMapperUtils.map(historyDTO, TransactionsHistory.class));
	}

	@Override
	public void deleteTransactionHistoryById(int id) {
		historyRepository.deleteTransactionById(id);
		
	}

	@Override
	public TransactionsHistoryDTO getTransactionHistoryById(int id) {
		return objectMapperUtils.map(historyRepository.getById(id), TransactionsHistoryDTO.class);
	}

	@Override
	public List<TransactionsHistoryDTO> getAllTransactionsByCardNumber(String cardNumber) {
		return objectMapperUtils.mapAll(historyRepository.getAllByCardNumber(cardNumber), TransactionsHistoryDTO.class);
	}

	
	
}
