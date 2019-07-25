package com.example.demo.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class NumberGenerator {

	private final Random RANDOM = new SecureRandom();
	private final String NUMBERS = "0123456789";
	
	public String generate() {
		return generateRandomString(16);
	}
	
	private String generateRandomString(int length) {
		StringBuilder builder = new StringBuilder(length);
		
		for(int i = 0; i < length; i++) {
			builder.append(NUMBERS.charAt(RANDOM.nextInt(NUMBERS.length())));
		}
		
		return new String(builder);
	}
	
	
}
