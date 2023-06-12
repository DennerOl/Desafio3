package com.devsuperior.desafio3.dto;

import java.time.LocalDate;

import com.devsuperior.desafio3.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ClientDTO {

	private long id;
	@Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
	@NotBlank(message = "Deve conter um nome")
	private String name;
	
	@Size(min = 11, max = 11, message = "O campo nome deve ter entre 11 caracteres")
	@NotBlank(message = "Deve conter um CPF")
	private String cpf;
	
	@Positive(message = "A renda deve ser positiva")
	private Double income;
	
	@PastOrPresent(message = "A data não pode ser futura")
	private LocalDate birthDate;
	
	@Positive(message = "Deve conter um valor positivo")
	private Integer children;
	


	public ClientDTO(long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public ClientDTO(Client entidade) {
		id = entidade.getId();
		name = entidade.getName();
		cpf = entidade.getCpf();
		income = entidade.getIncome();
		birthDate = entidade.getBirthDate();
		children = entidade.getChildren();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}
	
	
}
