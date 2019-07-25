package com.example.demo.domain;

import java.math.BigDecimal;

import com.example.demo.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class CreditCardDTO {

	private int id;
	
	private String cardNumber;
	
	private String password;
	
	private BigDecimal accountBalance;
	
	private AccountType accountType;

	private ClientDTO clientDTO;
}
