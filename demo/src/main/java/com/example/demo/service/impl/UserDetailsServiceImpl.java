package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CreditCard;
import com.example.demo.repository.CreditCardRepository;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CreditCardRepository cardRepository;
	
	@Override
	public UserDetails loadUserByUsername(String cardNumber) throws UsernameNotFoundException {
		CreditCard creditCard  = cardRepository.findByCardNumber(cardNumber);
		if(creditCard == null) {
			throw new UsernameNotFoundException("CreditCard with number [" + cardNumber + "] not found!");
		}
		return User
				.builder()
				.username(creditCard.getCardNumber())
				.password(creditCard.getPassword())
				.build();
	}

}
