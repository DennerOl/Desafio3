package com.devsuperior.desafio3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.desafio3.dto.ClientDTO;
import com.devsuperior.desafio3.entities.Client;
import com.devsuperior.desafio3.repositories.ClientRepository;
import com.devsuperior.desafio3.service.exceptions.NotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public ClientDTO buscarPorId(Long id) {
		Client result = repository.findById(id).orElseThrow(
				() -> new NotFoundException("Cliente não existente"));
		return new ClientDTO(result);
		
	}
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> buscaPaginada(Pageable pageable) { 
		Page<Client> result = repository.findAll(pageable);
		return result.map(x -> new ClientDTO(x));
	}
	
	@Transactional
	public ClientDTO inserir (ClientDTO dto) {
		Client entidade = new Client();
		copiaDto(dto, entidade);
		entidade = repository.save(entidade);
		return new ClientDTO(entidade);
	}
	
	@Transactional
	public ClientDTO atualizar (Long id, ClientDTO dto) {
		Client entidade = repository.getReferenceById(id);
		copiaDto(dto, entidade);
		entidade = repository.save(entidade);
		return new ClientDTO(entidade);
	}
	
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	
	public void copiaDto (ClientDTO dto, Client entidade) {
		entidade.setName(dto.getName());
		entidade.setCpf(dto.getCpf());
		entidade.setIncome(dto.getIncome());
		entidade.setBirthDate(dto.getBirthDate());
		entidade.setChildren(dto.getChildren());
		
	}
}
