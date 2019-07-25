package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Client;

@Repository
public interface ClientRepository {

	int count();
	
	void save(Client client);
	
	void update(Client client);
	
	void delete(int id);
	
	Client getById(int id);
}
