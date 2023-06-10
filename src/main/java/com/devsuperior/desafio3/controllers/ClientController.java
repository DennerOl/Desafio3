package com.devsuperior.desafio3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.desafio3.dto.ClientDTO;
import com.devsuperior.desafio3.service.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> buscarPorId(@PathVariable Long id) {
		ClientDTO dto = service.buscarPorId(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> buscaPaginada(Pageable pageable){
		Page<ClientDTO> dto = service.buscaPaginada(pageable);
		return ResponseEntity.ok(dto);
	}
}
