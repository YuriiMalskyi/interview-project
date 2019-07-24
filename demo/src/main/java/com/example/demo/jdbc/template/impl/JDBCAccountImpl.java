package com.example.demo.jdbc.template.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.TransactionsHistoryDTO;
import com.example.demo.entity.Account;
import com.example.demo.jdbc.template.JDBCAccount;

@Repository
public class JDBCAccountImpl implements JDBCAccount{

	@Override
	public void withdrawMoney(int summ) {
		
	}

	@Override
	public void sendMoneyTo(Account account) {
		
	}

	@Override
	public void putMoney(int summ) {
		
	}

	@Override
	public List<TransactionsHistoryDTO> getTransactionsHistory(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

}
