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

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Optional<ClientDTO> buscarPorId(Long id) {
        Optional<Client> result = repository.findById(id);
        Client client = result.orElseGet(()-> null);
        Optional<ClientDTO> dto = Optional.empty();
        if (client != null) {
            dto = Optional.of(new ClientDTO(client));
        }
        return dto;

    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> buscaPaginada(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

}
