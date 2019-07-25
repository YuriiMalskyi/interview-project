package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.ClientDTO;
import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import com.example.demo.utils.ObjectMapperUtils;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ObjectMapperUtils objectMapperUtils;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public void createClient(ClientDTO client) {
		clientRepository.save(objectMapperUtils.map(client, Client.class));
	}

	@Override
	public void editClient(ClientDTO client) {
		clientRepository.update(objectMapperUtils.map(client, Client.class));
	}

	@Override
	public void deleteClient(ClientDTO client) {
		clientRepository.delete(objectMapperUtils.map(client, Client.class));
	}

	@Override
	public void deleteClientById(int id) {
		clientRepository.deleteById(id);
	}

	@Override
	public ClientDTO getClientById(int id) {
		return objectMapperUtils.map(clientRepository.getById(id), ClientDTO.class);
	}

}
