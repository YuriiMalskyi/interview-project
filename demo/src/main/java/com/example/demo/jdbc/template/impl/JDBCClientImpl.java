package com.example.demo.jdbc.template.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.domain.ClientDTO;
import com.example.demo.jdbc.template.JDBCClient;

public class JDBCClientImpl implements JDBCClient{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	@Override
	public void createNewClient(ClientDTO client) {
		
		String sql = "INSERT INTO EMPLOYEE " +
	            "(ID, NAME, AGE) VALUES (?, ?, ?)";
						
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(sql, new Object[] { 
				client.getFirstName(), 
				client.getLastName(), 
				client.getPassportData(), 
				client.getPassword(),
				client.getAccount()
        });
		
		
	}

}
