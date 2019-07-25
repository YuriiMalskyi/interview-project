package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.ClientDTO;
import com.example.demo.service.ClientService;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{

	@Autowired
	private com.example.demo.utils.ObjectMapperUtils objectMapperUtils;
	
	
	@Override
	public void createClient(ClientDTO client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editClient(ClientDTO client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteClient(ClientDTO client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteClientById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClientDTO getClientById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
