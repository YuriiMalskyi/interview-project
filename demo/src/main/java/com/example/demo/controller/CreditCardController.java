package com.example.demo.controller;

import java.math.BigDecimal;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.CreditCardDTO;
import com.example.demo.service.CreditCardService;

@RestController
@RequestMapping("credit-card")
public class CreditCardController {

	@Autowired
	private CreditCardService creditCardService;
	
	@PostMapping
	public ResponseEntity<Void> addCreditCard(@RequestBody CreditCardDTO cardDTO){
		creditCardService.createCreditCard(cardDTO);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Void> editCreditCard(@RequestBody CreditCardDTO cardDTO){
		creditCardService.editCreditCard(cardDTO);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteCreditCard(@RequestBody CreditCardDTO cardDTO){
		creditCardService.deleteCreditCard(cardDTO);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{card_number}")
	public ResponseEntity<Void> deleteCreditCardByCardNumber(@PathVariable("card_number") String cardNumber){
		creditCardService.deleteCreditCardByCardNumber(cardNumber);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/{card_number}")
	public ResponseEntity<CreditCardDTO> getCreditCardByCardNumber(@PathVariable("card_number") String cardNumber){
		return new ResponseEntity<CreditCardDTO>(creditCardService.getCreditCardByCardNumber(cardNumber), HttpStatus.OK);
	}
	
	@GetMapping("/balance/{card_number}")
	public ResponseEntity<BigDecimal> getCreditCardBalanceByCardNumber(@PathVariable("card_number") String cardNumber){
		return new ResponseEntity<BigDecimal>(creditCardService.getCreditCardBalance(cardNumber), HttpStatus.OK);
	}
	
	@PutMapping("/balance/refill/{card_number}-{summ}")
	public ResponseEntity<Void> refillCreditCardBalanceByCardNumber(@PathVariable("card_number") String cardNumber, @PathVariable("summ") BigDecimal summ){
		creditCardService.refillCreditCardBalance(cardNumber, summ);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/transaction/{sender}-{reciever}/{summ}")
	public ResponseEntity<Void> sendMoneyFromTo(@PathVariable("sender") String sendingCardNumber, @PathVariable("reciever") String recievingCartNumber, 
			@PathVariable("summ") BigDecimal summ, @RequestParam("message") String message){
		creditCardService.sendMoneyFromTo(sendingCardNumber, recievingCartNumber, summ, message);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/balance/withdraw/{card_number}-{summ}")
	public ResponseEntity<Void> withdrawCreditCardBalanceByCardNumber(@PathVariable("card_number") String cardNumber, @PathVariable("summ") BigDecimal summ){
		creditCardService.withdrawCreditCardBalance(cardNumber, summ);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/exists/{id}")
	public ResponseEntity<Boolean> existsById(@PathVariable("id") int id){
		return new ResponseEntity<Boolean>(creditCardService.existsById(id), HttpStatus.OK);
	}
	
	@GetMapping("/exists/credir-card-{card_number}")
	public ResponseEntity<Boolean> existsById(@PathVariable("card_number") String cardNumber){
		return new ResponseEntity<Boolean>(creditCardService.existsByCreditCardNumber(cardNumber), HttpStatus.OK);
	}
}
