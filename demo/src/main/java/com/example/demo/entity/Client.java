package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	private String firstName;
	
	private String lastName;
		
	@Column(nullable = false, unique = true)
	private String passportData;
	
	public Client(Client client) {
		this.firstName = client.firstName;
		this.lastName = client.lastName;
		this.passportData = client.passportData;
	}

	@Override
	public String toString() {
		return "Client [firstName=" + firstName + ", lastName=" + lastName + ", passportData=" + passportData + "]";
	}
	
}
