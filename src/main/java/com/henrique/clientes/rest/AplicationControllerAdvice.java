package com.henrique.clientes.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.henrique.clientes.rest.exceptions.ApiErrors;

@RestControllerAdvice
public class AplicationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handlerValidationErros(MethodArgumentNotValidException ex) {

		BindingResult bindingResout = ex.getBindingResult();
		List<String> messages = bindingResout.getAllErrors()
				.stream()
				.map(objectError -> objectError.getDefaultMessage())
				.collect(Collectors.toList());
		return new ApiErrors(messages);
	}
	
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity handleResponseStatusRxception(ResponseStatusException ex) {
		String messageErro = ex.getReason();
		HttpStatus codigoStatus = ex.getStatus();
		ApiErrors apiErrors = new ApiErrors(messageErro);
		return new ResponseEntity(apiErrors,codigoStatus);
		
	}

}
