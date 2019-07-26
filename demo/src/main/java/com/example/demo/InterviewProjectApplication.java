package com.example.demo;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.domain.ClientDTO;
import com.example.demo.domain.CreditCardDTO;
import com.example.demo.domain.TransactionsHistoryDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.CreditCard;
import com.example.demo.enums.AccountType;
import com.example.demo.enums.Operation;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.repository.TransactionsHistoryRepository;
import com.example.demo.service.CreditCardService;
import com.example.demo.service.TransactionsHistoryService;
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
	private CreditCardService cardService;
	
	@Autowired
	private TransactionsHistoryService historyService;
	
	@Autowired
	private ObjectMapperUtils objectMapperUtils;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		
		if(clientRepository.count() == 0) {
			ClientDTO bank = new ClientDTO();
//			bank.setId(1);
			bank.setFirstName("Bank");
			bank.setPassportData("BANK");
			clientRepository.save(objectMapperUtils.map(bank, Client.class));
			
		ClientDTO client = new ClientDTO();
//		client.setId(2);
		client.setFirstName("Yurii");
		client.setLastName("Malskyi");
		client.setPassportData("igaf234DES732jdsUYS9ei");
		
		clientRepository.save(objectMapperUtils.map(client, Client.class));
		}

		if(cardRepository.count() == 0) {
			CreditCard card = new CreditCard();
			card.setPassword("1234");
			card.setAccountBalance(new BigDecimal(10000.00));
			card.setAccountType(AccountType.UAH);
			card.setClient(clientRepository.getById(1));
			
			cardRepository.save(card);
		}
		
		if(transactionsHistoryRepository.count() < 1) {
			TransactionsHistoryDTO h1 = new TransactionsHistoryDTO();
			h1.setDate(new Date());
//			h1.setTo(cardService.getCreditCardByCardNumber("4012471872242018"));
			h1.setSumm(new BigDecimal(20000.00));
			h1.setOperation(Operation.REFILL);
			h1.setMessage("test");
			System.out.println("\n" + h1.toString() + "\n");
			historyService.createTransactionHistory(h1);
		}
		
	}

}
