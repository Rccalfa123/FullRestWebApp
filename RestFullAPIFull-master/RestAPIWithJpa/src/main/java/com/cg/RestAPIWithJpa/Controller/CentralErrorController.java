package com.cg.RestAPIWithJpa.Controller;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.RestAPIWithJpa.exceptions.AmountNoValidException;
import com.cg.RestAPIWithJpa.exceptions.Insufficient_Balance_Exception;
import com.cg.RestAPIWithJpa.exceptions.NoAccountsToDisplayException;
import com.cg.RestAPIWithJpa.exceptions.PageNotFoundException;
import com.cg.RestAPIWithJpa.pojo.ErrorResponse;

@ControllerAdvice
public class CentralErrorController {

	private ErrorResponse errorResponse= new ErrorResponse();
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException exception)
	{
		errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setErrorMessage(exception.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
		
	}
	

	@ExceptionHandler(AmountNoValidException.class)
	public ResponseEntity<ErrorResponse> handleAmountNoValidException(AmountNoValidException exception)
	{
		errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setErrorMessage(exception.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
		
	}
	

	@ExceptionHandler(Insufficient_Balance_Exception.class)
	public ResponseEntity<ErrorResponse> handleInsufficient_Balance_Exception(Insufficient_Balance_Exception exception)
	{
		errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setErrorMessage(exception.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
		
	}
	

	@ExceptionHandler(NoAccountsToDisplayException.class)
	public ResponseEntity<ErrorResponse> handleNoAccountsToDisplayException(NoAccountsToDisplayException exception)
	{
		errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setErrorMessage(exception.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
		
	}
	

	@ExceptionHandler(PageNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlePageNotFoundException(PageNotFoundException exception)
	{
		errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setErrorMessage(exception.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
		
	}
}
