package com.projeto.crud.modules.cliente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.crud.modules.cliente.ClientRepository;

import java.util.UUID;

@Service
public class DeleteClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void deleteClient(UUID id) {
        clientRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clientRepository.deleteById(id);
    }

}
