package com.example.demo;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.domain.ClientDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.CreditCard;
import com.example.demo.enums.AccountType;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.repository.TransactionsHistoryRepository;
import com.example.demo.repository.impl.JdbcClientRepository;
import com.example.demo.utils.ObjectMapperUtils;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableJpaRepositories(basePackages = "com.example.demo.repository")
public class InterviewProjectApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(InterviewProjectApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CreditCardRepository cardRepository;

	@Autowired
	private TransactionsHistoryRepository transactionsHistoryRepository;
	
	@Autowired
	private ObjectMapperUtils objectMapperUtils;
	
	@Override
	public void run(String... args) throws Exception {
		
		if(clientRepository.count() == 0) {
		
		ClientDTO client = new ClientDTO();
		client.setFirstName("Yurii");
		client.setLastName("Malskyi");
		client.setPassportData("igaf234DES732jdsUYS9ei");
		
		clientRepository.save(objectMapperUtils.map(client, Client.class));
		}else {
//			System.out.println("Records exists in [client] table! YAY!\n	Selecting the rows");
//			Client client = clientRepository.getById(1);
//			System.out.println(client.toString());
		}
		
//		if(cardRepository.count() == 0) {
//			CreditCard card = new CreditCard();
//			card.setPassword("1234");
//			card.setAccountBalance(new BigDecimal(10000.00));
//			card.setAccountType(AccountType.UAH);
//			card.setClient(clientRepository.getById(1));
//			
//			cardRepository.save(card);
//		}else {
			CreditCard card = cardRepository.findByCardNumber("2993176808564627");
			System.out.println("\n\n\n ---------------  Card:  --------------- \n"+card.toString()+"\n\n\n");
			card.setClient(clientRepository.getById(1));
			System.out.println(card.toString());
//			
//			card.setAccountType(AccountType.DOLLAR);
//			cardRepository.update(card);
//		}
	}

}
