package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.TransactionsHistoryDTO;
import com.example.demo.service.TransactionsHistoryService;

@RestController
@RequestMapping("transactions-history")
public class TransactionsHistoryController {
	
	@Autowired
	private TransactionsHistoryService historyService;

	@PostMapping
	public ResponseEntity<Void> addTransactionsHistory(@RequestBody TransactionsHistoryDTO historyDTO){
		historyService.createTransactionHistory(historyDTO);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
		
	@DeleteMapping
	public ResponseEntity<Void> deleteTransactionsHistory(@RequestBody TransactionsHistoryDTO historyDTO){
		historyService.deleteTransactionHistory(historyDTO);
		return new ResponseEntity<Void>(HttpStatus.OK);	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTransactionsHistoryById(@PathVariable("id") int id){
		historyService.deleteTransactionHistoryById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);	
	}
		
	@GetMapping("/{card_number}")
	public ResponseEntity<List<TransactionsHistoryDTO>> getAllTransactionsHistoryByCardNumber(@PathVariable("card_number") String cardNumber){
		return new ResponseEntity<List<TransactionsHistoryDTO>>(historyService.getAllTransactionsByCardNumber(cardNumber), HttpStatus.OK);
	}
	
}
