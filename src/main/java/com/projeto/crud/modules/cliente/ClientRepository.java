package com.projeto.crud.modules.cliente;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByEmail(String email);

    Optional<Client> findByCpf(String cpf);

    Optional<Client> findByPhone(String phone);
}
