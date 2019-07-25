package com.example.demo.domain;

import java.util.Date;

import com.example.demo.entity.CreditCard;
import com.example.demo.enums.Operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionsHistoryDTO {

	private int id;
	
	private Date date;
	
	private CreditCard from;
	
	private CreditCard to;
	
	private int summ;
	
	private Operation operation;
	
	private String message;

	public TransactionsHistoryDTO(Date date, CreditCard from, CreditCard to, int summ, Operation operation,
			String message) {
		super();
		this.date = date;
		this.from = from;
		this.to = to;
		this.summ = summ;
		this.operation = operation;
		this.message = message;
	}
	
	
}
