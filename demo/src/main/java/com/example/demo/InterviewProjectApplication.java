package com.example.demo;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.example.demo.domain.ClientDTO;
import com.example.demo.domain.CreditCardDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.CreditCard;
import com.example.demo.entity.TransactionsHistory;
import com.example.demo.enums.AccountType;
import com.example.demo.enums.Operation;
import com.example.demo.enums.Roles;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.repository.TransactionsHistoryRepository;
import com.example.demo.service.CreditCardService;
import com.example.demo.utils.ObjectMapperUtils;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableJpaRepositories(basePackages = "com.example.demo.repository")
public class InterviewProjectApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(InterviewProjectApplication.class, args);
	}

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CreditCardRepository cardRepository;
	
	@Autowired 
	private CreditCardService cardService;

	@Autowired
	private ObjectMapperUtils objectMapperUtils;
	
	@Autowired
	private TransactionsHistoryRepository transactionsHistoryRepository;
			
	@Override
	public void run(String... args) throws Exception {
				
		if(clientRepository.count() == 0) {
			
			clientRepository.save(new Client("Bank", "", "BANKDATA"));
			
			clientRepository.save(new Client("Yurii", "Malskyi", "myPassportData"));
			
		}

		if(cardRepository.count() == 0) {
			
			CreditCard c1 = new CreditCard("", "1234", new BigDecimal(500000.00), AccountType.DOLLAR, Roles.CLIENT, clientRepository.getById(1));
			CreditCardDTO c1m = objectMapperUtils.map(c1, CreditCardDTO.class);
			c1m.setClientDTO(objectMapperUtils.map(c1.getClient(), ClientDTO.class));
//			
			cardService.createCreditCard(c1m
////					objectMapperUtils.map(c1m, CreditCardDTO.class)
					);
//			
//			cardService.createCreditCard(
//					objectMapperUtils.map(new CreditCard("", "1111", new BigDecimal(10000.00), AccountType.UAH, Roles.CLIENT, clientRepository.getById(2)), CreditCardDTO.class)
//					);
		}
		
//		if(transactionsHistoryRepository.count() < 1) {
//			TransactionsHistory h1 = new TransactionsHistory();
//			h1.setDate(new Date());
////			h1.setTo(cardService.getCreditCardByCardNumber("4012471872242018"));
//			h1.setSumm(new BigDecimal(20000.00));
//			h1.setOperation(Operation.REFILL);
//			h1.setMessage("test");
//			System.out.println("\n" + h1.toString() + "\n");
//			transactionsHistoryRepository.save(h1);
//		}
		
	}

}
