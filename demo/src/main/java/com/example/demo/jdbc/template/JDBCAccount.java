package com.example.demo.jdbc.template;

import java.util.List;

import com.example.demo.domain.TransactionsHistoryDTO;
import com.example.demo.entity.Account;

public interface JDBCAccount {

	void withdrawMoney(int summ);
	
	void sendMoneyTo(Account account);
	
	void putMoney(int summ);
		
	List<TransactionsHistoryDTO> getTransactionsHistory(Account account);
	
}
