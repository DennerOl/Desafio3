package com.devsuperior.desafio3.controllers.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.desafio3.dto.CustomError;
import com.devsuperior.desafio3.dto.ValidationError;
import com.devsuperior.desafio3.service.exceptions.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ContrExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<CustomError> notFoundException(NotFoundException e, HttpServletRequest request) {
	HttpStatus status = HttpStatus.NOT_FOUND;
	CustomError err = new CustomError(Instant.now(), status.value(),e.getMessage(), request.getRequestURI());
	return ResponseEntity.status(status).body(err);
	}
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
	HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
	ValidationError err = new ValidationError(Instant.now(), status.value(),"Erro nos dados digitados", request.getRequestURI());
	
	for (FieldError f : e.getBindingResult().getFieldErrors()) {
		err.addErros(f.getField(),f.getDefaultMessage());
	}
	return ResponseEntity.status(status).body(err);
	}
	
}
