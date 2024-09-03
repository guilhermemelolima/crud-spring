package com.projeto.crud.modules.cliente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.crud.modules.cliente.Client;
import com.projeto.crud.modules.cliente.ClientMapper;
import com.projeto.crud.modules.cliente.ClientRepository;
import com.projeto.crud.modules.cliente.dto.ClientDTO;

@Service
public class CreateClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO createCliente(Client client) {
        System.out.println(client.getEmail());
        clientRepository
                .findByEmail(client.getEmail())
                .ifPresent(existing -> {
                    throw new RuntimeException("Email já está cadastrado.");
                });

        System.out.println(client.getPhone());
        clientRepository
                .findByPhone(client.getPhone())
                .ifPresent(existing -> {
                    throw new RuntimeException("Telefone já cadastrado.");
                });

        System.out.println(client.getCpf());
        clientRepository
                .findByCpf(client.getCpf())
                .ifPresent(existing -> {
                    throw new RuntimeException("CPF já cadastrado.");
                });

        return ClientMapper.toDTO(clientRepository.save(client));
    }
}
