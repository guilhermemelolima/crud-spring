package com.projeto.crud.modules.cliente.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private UUID id;
    private String name;
    private String email;
    private String cpf;
    private String phone;
    // private String role;
}
