package com.example.demo.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientDTO {

	private String passportData;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private AccountDTO account;
}
