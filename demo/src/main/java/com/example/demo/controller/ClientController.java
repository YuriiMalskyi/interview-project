package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ClientDTO;
import com.example.demo.service.ClientService;

@RestController
@RequestMapping("client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@PostMapping
	public ResponseEntity<Void> addClient(@RequestBody ClientDTO clientDTO){
		clientService.createClient(clientDTO);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Void> editClient(@RequestBody ClientDTO clientDTO){
		clientService.editClient(clientDTO);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteClient(@RequestBody ClientDTO clientDTO){
		clientService.deleteClient(clientDTO);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable("id") int id){
		clientService.deleteClientById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") int id){
		return new ResponseEntity<ClientDTO>(clientService.getClientById(id), HttpStatus.OK);
	}
	
	
}
