package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.example.demo.enums.Operations;

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
@Table(name = "transactions_history")
public class TransactionsHistory {

	@Column(nullable = false)
	private int summ;
	
	@Column(nullable = false)
	private Date date;
	
	private Account from;
	
	private Account to;
	
	@Enumerated(EnumType.STRING)
	private Operations operation;
}
