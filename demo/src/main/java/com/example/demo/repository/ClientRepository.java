package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Client;

@Repository
public interface ClientRepository {

	int count();
	
	void save(Client client);
	
	void update(Client client);
	
	void delete(Client client);
	
	void deleteById(int id);
	
	Client getById(int id);
	
	boolean existsById(int id);
	
	boolean existsByPassportData(String passportData);
	
}
