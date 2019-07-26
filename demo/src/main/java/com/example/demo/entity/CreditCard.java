package com.example.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.example.demo.enums.AccountType;

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
@Table(name = "credit_card", indexes = @Index(columnList = "cardnumber"))
public class CreditCard extends BaseEntity {
	
	@Column(nullable = false, unique = true, length = 16)
	private String cardNumber;
	
	@Column(nullable = false, length = 4)
	private String password;
	
	@Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
	private BigDecimal accountBalance;
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@OneToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@Override
	public String toString() {
		return "CreditCard [cardNumber=" + cardNumber + ", password=" + password + ", accountBalance=" + accountBalance
				+ ", accountType=" + accountType + ", client=" + client + "]";
	}

}
