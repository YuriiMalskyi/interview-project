package com.example.demo.service;

import com.example.demo.domain.ClientDTO;

public interface ClientService {

	void createClient(ClientDTO client);
	
	void editClient(ClientDTO client);

	void deleteClient(ClientDTO client);
	
	void deleteClientById(int id);
	
	ClientDTO getClientById(int id);
	
	boolean existsById(int id);
	
	boolean existsByPassportData(String passportData);
}
