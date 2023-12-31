package com.devsuperior.desafio3.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;

public class ValidationError extends CustomError {

	private List<FieldMessage> erros = new ArrayList<>();
	
	
	public ValidationError(Instant timestamp, Integer status, String error, String path) {
		super(timestamp, status, error, path);
		
	}


	public List<FieldMessage> getErros() {
		return erros;
	}

	public void addErros(String fieldName, String message) {
		erros.add(new FieldMessage(fieldName, message));
	}
}
