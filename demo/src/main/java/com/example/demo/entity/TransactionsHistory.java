package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.enums.Operation;

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
public class TransactionsHistory extends BaseEntity {
	
	@Temporal(TemporalType.DATE)
	@Column(name = "operation_date", nullable = false)
	private Date date;
	
	@OneToOne
	@JoinColumn(name = "account_from_id", nullable = true)
	private CreditCard from;
	
	@OneToOne
	@JoinColumn(name = "account_to_id", nullable = true)
	private CreditCard to;
	
	@Column(nullable = false)
	private BigDecimal summ;
	
	@Enumerated(EnumType.STRING)
	private Operation operation;

	private String message;

	@Override
	public String toString() {
		return "TransactionsHistory [date=" + date + ", from=" + from + ", to=" + to + ", summ=" + summ + ", operation="
				+ operation + ", message=" + message + "]";
	}

	public TransactionsHistory(int id, Date date, CreditCard from, CreditCard to, BigDecimal summ, Operation operation,
			String message) {
		setId(id);
		this.date = date;
		this.from = from;
		this.to = to;
		this.summ = summ;
		this.operation = operation;
		this.message = message;
	}
	
	
	
}
