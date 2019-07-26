package com.example.demo.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.domain.response.ErrorMessage;
import com.example.demo.exceptions.ClientServiceException;
import com.example.demo.exceptions.CreditCardNotFoundException;
import com.example.demo.exceptions.WithdrawSumException;


@ControllerAdvice
public class ServerExceptionsHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<com.example.demo.domain.response.ErrorMessage> handleExceptions(Exception e, WebRequest req){
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = ClientServiceException.class)
	public ResponseEntity<ErrorMessage> handleUserServiceExceptions(ClientServiceException e, WebRequest req){
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.SERVICE_UNAVAILABLE);
	}	

	@ExceptionHandler(value = WithdrawSumException.class)
	public ResponseEntity<ErrorMessage> handleWithdrawSumExceptions(WithdrawSumException e, WebRequest req){
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = CreditCardNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleAccountNotFoundExceptions(CreditCardNotFoundException e, WebRequest req){
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	}
		
}
