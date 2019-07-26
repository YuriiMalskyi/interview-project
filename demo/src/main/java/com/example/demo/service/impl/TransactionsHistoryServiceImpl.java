package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.CreditCardDTO;
import com.example.demo.domain.TransactionsHistoryDTO;
import com.example.demo.entity.CreditCard;
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
		TransactionsHistory history;
		
		history = objectMapperUtils.map(historyDTO, TransactionsHistory.class);
		
		if(historyDTO.getFrom() != null) {
			history.setFrom(objectMapperUtils.map(historyDTO.getFrom(), CreditCard.class));
		}else {
			history.setFrom(new CreditCard());
		}
		if(historyDTO.getTo() != null) {
			history.setTo(objectMapperUtils.map(historyDTO.getTo(), CreditCard.class));
		}else {
			history.setTo(new CreditCard());
		}
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
		
		TransactionsHistory history = historyRepository.getById(id);
		
		TransactionsHistoryDTO historyDTO = objectMapperUtils.map(history, TransactionsHistoryDTO.class);
		
		historyDTO.setFrom(objectMapperUtils.map(history.getFrom(), CreditCardDTO.class));
		historyDTO.setTo(objectMapperUtils.map(history.getTo(), CreditCardDTO.class));
		
		return historyDTO;
		
	}

	@Override
	public List<TransactionsHistoryDTO> getAllTransactionsByCardNumber(String cardNumber) {
		List<TransactionsHistory> elist = historyRepository.getAllByCardNumber(cardNumber);
//		List<TransactionsHistoryDTO> list = objectMapperUtils.mapAll(historyRepository.getAllByCardNumber(cardNumber), TransactionsHistoryDTO.class);
		
		List<TransactionsHistoryDTO> list = new LinkedList<TransactionsHistoryDTO>();// = objectMapperUtils.mapAll(elist, TransactionsHistoryDTO.class);
						
		for(Iterator<TransactionsHistory> iter = elist.iterator(); iter.hasNext(); ) {
			TransactionsHistory history = iter.next();
			
			System.out.println("\n" + history.toString() + "");
			TransactionsHistoryDTO dto = new TransactionsHistoryDTO();
			dto = objectMapperUtils.map(history, TransactionsHistoryDTO.class);
			dto.setFrom(objectMapperUtils.map(history.getFrom(), CreditCardDTO.class));
			dto.setTo(objectMapperUtils.map(history.getTo(), CreditCardDTO.class));
			System.out.println("\n" + dto.toString() + "\n\n");
			list.add(dto);
		}
//		Iterator<TransactionsHistoryDTO> iter = list.iterator();
		
		
//		return objectMapperUtils.mapAll(historyRepository.getAllByCardNumber(cardNumber), TransactionsHistorDTO.class);
		return list;
	}

	@Override
	public boolean existsById(int id) {
		return historyRepository.existsById(id);
	}

	
	
}
