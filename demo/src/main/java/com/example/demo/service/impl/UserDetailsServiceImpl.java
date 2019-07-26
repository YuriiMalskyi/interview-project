package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.CreditCard;
import com.example.demo.repository.CreditCardRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CreditCardRepository cardRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CreditCard creditCard  = cardRepository.findByCardNumber(username);
		if(creditCard == null) {
			throw new UsernameNotFoundException("CreditCard with number [" + username + "] not found!");
		}
		
		System.out.println("UserDetailsServiceImpl.loadUserByUsername() method called ");
		System.out.println(creditCard.toString());
		return User
				.builder()
				.username(creditCard.getCardNumber())
				.password(creditCard.getPassword())
				.authorities(creditCard.getRole())
				.build();
	}

}
