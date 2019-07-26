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
                "update client set first_name = ?, last_name = ?, passport_data = ? where id = ?",
                client.getFirstName(), client.getLastName(), client.getPassportData(), client.getId());
	}

	@Override
	public void delete(Client client) {
		jdbcTemplate.update("delete from client where id = ?", client.getId());		
	}
	
	@Override
	public void deleteById(int id) {		
		jdbcTemplate.update("delete from client where id = ?", id);
	}

	@Override
	public Client getById(int id) {
		System.out.println("\n------------------------\nIncomming value to getById(int id) method :\n" + id + "\n------------------------\n");
		Client cl =  jdbcTemplate.queryForObject("select * from client where id = " + id, new BeanPropertyRowMapper<Client>(Client.class));
		cl.toString();
		return cl;
	}

	@Override
	public boolean existsById(int id) {
		return jdbcTemplate.queryForObject("select count(*) from client where id = " + id, Integer.class) < 1 ? false : true ;
	}

	@Override
	public boolean existsByPassportData(String passportData) {
		return jdbcTemplate.queryForObject("select count(*) from client where passport_data = \'" + passportData + "\'", Integer.class) < 1 ? false : true ;
	}


}
