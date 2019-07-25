package com.example.demo.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;

@Repository
public class JdbcClientRepository implements ClientRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	@Override
	public int count() {
		 return jdbcTemplate
	                .queryForObject("select count(*) from client", Integer.class);
	}
	
	@Override
	public void save(Client client) {
		jdbcTemplate.update(
                "insert into client (first_name, last_name, passport_data) values(?,?,?)",
	                client.getFirstName(), client.getLastName(), client.getPassportData());		
	}

	@Override
	public void update(Client client) {
		jdbcTemplate.update(
                "update client set firstName = ?, lastName = ?, passportData = ? where id = ?",
                client.getFirstName(), client.getLastName(), client.getPassportData(), client.getId());
	}

	@Override
	public void delete(int id) {		
		jdbcTemplate.update("delete client where id = ?", id);
	}

	@Override
	public Client getById(int id) {
		System.out.println("\n------------------------\nIncomming value to getById(int id) method :\n" + id + "\n------------------------\n");
		return jdbcTemplate.queryForObject("select * from client where id = " + id, new BeanPropertyRowMapper<Client>(Client.class));
	}

}
