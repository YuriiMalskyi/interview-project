package com.example.demo.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.example.demo.enums.Operation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionsHistoryDTO {

	private int id;
	
	private Date date;
	
	private CreditCardDTO from;
	
	private CreditCardDTO to;
	
	private BigDecimal summ;
	
	private Operation operation;
	
	private String message;
	
}
