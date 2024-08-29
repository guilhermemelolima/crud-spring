package com.projeto.crud.modules.cliente.services;

import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.crud.modules.cliente.Client;
import com.projeto.crud.modules.cliente.ClientMapper;
import com.projeto.crud.modules.cliente.ClientRepository;
import com.projeto.crud.modules.cliente.dto.ClientDTO;

@Service
public class ReadClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO getClientById(UUID id) {
        return toClientDTO(clientRepository.findById(id));
    }

    public ClientDTO getClientByEmail(String email) {
        return toClientDTO(clientRepository.findByEmail(email));
    }

    public ClientDTO getClientByCpf(String cpf) {
        return toClientDTO(clientRepository.findByCpf(cpf));
    }

    public ClientDTO getClientByPhone(String phone) {
        return toClientDTO(clientRepository.findByPhone(phone));
    }

    // Método privado para tratar a lógica comum de conversão e exceções
    private ClientDTO toClientDTO(Optional<Client> clientOptional) {
        Client client = clientOptional.orElseThrow(() -> new RuntimeException("Cliente não encontrado "));
        return ClientMapper.toDTO(client);
    }
}
