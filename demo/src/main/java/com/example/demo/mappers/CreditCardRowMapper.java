package com.example.demo.mappers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Client;
import com.example.demo.entity.CreditCard;
import com.example.demo.enums.AccountType;
import com.example.demo.repository.ClientRepository;

import lombok.Data;

@Data
@Component
public class CreditCardRowMapper implements RowMapper<CreditCard> {

	@Autowired
//	@Lazy
	private ClientRepository clientRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public CreditCard mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ResultSetMetaData rsmd = rs.getMetaData();
		
		CreditCard card = new CreditCard();
		card.setId(rs.getInt("id"));
		card.setCardNumber(rs.getString("card_number"));
		card.setPassword(rs.getString("password"));
		card.setAccountBalance(rs.getBigDecimal("account_balance"));	
		
		card.setAccountType(AccountType.convertString(rs.getString("account_type")));
				
		System.out.println("\n------------------------\nclient_id value:\n	[ " + rs.getInt("client_id") + " ].class == " + rsmd.getColumnClassName(6) + "\n------------------------\n");
		
		Client client = new Client(clientRepository.getById(rs.getInt("client_id")));
		
		System.out.println(client.toString());
		
		System.out.println(client.toString());
		card.setClient(client);
//		card.setClient((Client)clientRepository.getById((int)rs.getInt("client_id")));
		return card;
	}

}
