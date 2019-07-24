package com.example.demo.jdbc.template;

import com.example.demo.domain.ClientDTO;

public interface JDBCClient {

	void createNewClient(ClientDTO client);
	
	
}
