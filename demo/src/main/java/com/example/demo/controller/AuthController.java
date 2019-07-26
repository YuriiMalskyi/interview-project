package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.request.LoginRequest;
import com.example.demo.domain.response.SigninResponse;
import com.example.demo.enums.Roles;
import com.example.demo.service.CreditCardService;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	private CreditCardService creditCardService;
	
	@PostMapping("signin")
	public ResponseEntity<SigninResponse> signin(@RequestBody LoginRequest request) {
		String token = creditCardService.signin(request.getCardNumber(), request.getPassword());
		String role = "";
		System.out.println(token + "\n" + request.getCardNumber() + "\n" + request.getPassword());
		if(token != null) {
			role = Roles.CLIENT.toString();
		}
		return new ResponseEntity<SigninResponse>(new SigninResponse(token, role), HttpStatus.OK);
	}

	
//	@PostMapping("signin")
//	public ResponseEntity<SigninResponse> signin(@RequestBody SigninRequest request) {
//		String token = userService.signin(request.getUsername(), request.getPassword());
//		String role = "";
//		System.out.println(token + "\n" + request.getUsername() + "\n" + request.getPassword());
//		
//		if(token != null) {
//			role = userService.findByUsername(request.getUsername()).getRole().toString();
//			System.out.println("ROLE: " + role);
//		}
//		
//		return new ResponseEntity<SigninResponse>(new SigninResponse(token, role), HttpStatus.OK);
//	}
}
