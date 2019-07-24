package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "client")
public class Client extends BaseEntity{

	@Column(nullable = false, unique = true)
	private String passportData;
		
	private String firstName;
	
	private String lastName;
	
	@Column(nullable = false)
	private String password;
	
	@OneToOne
	@JoinColumn(name = "transactions_history_id")
	private Account account;
}
