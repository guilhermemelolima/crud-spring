package com.projeto.crud.modules.cliente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.crud.modules.cliente.Client;
import com.projeto.crud.modules.cliente.ClientMapper;
import com.projeto.crud.modules.cliente.ClientRepository;
import com.projeto.crud.modules.cliente.dto.ClientDTO;

@Service
public class UpdateClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO updateCliente(Client client) {

        Client newClient = clientRepository.findById(client.getId())
                .orElseThrow(()-> new RuntimeException("Cliente não encontrado"));

        clientRepository.findByCpf(client.getCpf())
                .ifPresent(existingClient -> {
                    if(client.getId() != existingClient.getId()){
                        throw new RuntimeException("CPF já cadastrado na base de dados");
                    }
                });
        clientRepository.findByPhone(client.getPhone())
                .ifPresent(existingClient -> {
                    if(client.getId() != existingClient.getId()){
                        throw new RuntimeException("Telefone já cadastrado na base de dados");
                    }
                });
        clientRepository.findByEmail(client.getEmail())
                .ifPresent(existingClient -> {
                    if(client.getId() != existingClient.getId()){
                        throw new RuntimeException("E-mail já cadastrado base de dados");
                    }
                });

        newClient = Client
                .builder()
                .id(client.getId())
                .name(client.getName())
                .cpf(client.getCpf())
                .email(client.getEmail())
                .phone(client.getPhone())
                .build();

        return ClientMapper.toDTO(clientRepository.save(newClient));
    }
}