package com.example.demo.domain;

import java.math.BigDecimal;

import com.example.demo.enums.AccountType;
import com.example.demo.enums.Roles;

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

	private Roles role;
	
	private ClientDTO clientDTO;
}
