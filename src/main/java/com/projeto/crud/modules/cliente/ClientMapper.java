package com.projeto.crud.modules.cliente;

import com.projeto.crud.modules.cliente.dto.ClientDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ClientMapper {

    public static Client toEntity(ClientDTO dto) {
        return Client
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .phone(dto.getPhone())
                .build();
    }

    public static ClientDTO toDTO(Client cliente) {
        return ClientDTO
                .builder()
                .id(cliente.getId())
                .name(cliente.getName())
                .email(cliente.getEmail())
                .cpf(cliente.getCpf())
                .phone(cliente.getPhone())
                .build();
    }

    public static List<ClientDTO> toDTOList(List<Client> clients){
        return clients.stream().map(ClientMapper::toDTO).collect(Collectors.toList());
    }
}
