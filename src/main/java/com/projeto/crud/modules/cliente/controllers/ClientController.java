package com.projeto.crud.modules.cliente.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.crud.modules.cliente.Client;
import com.projeto.crud.modules.cliente.dto.ClientDTO;
import com.projeto.crud.modules.cliente.services.CreateClientService;
import com.projeto.crud.modules.cliente.services.DeleteClientService;
import com.projeto.crud.modules.cliente.services.ReadClientService;
import com.projeto.crud.modules.cliente.services.UpdateClientService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private CreateClientService createClientService;

    @Autowired
    private DeleteClientService deleteClientService;

    @Autowired
    private ReadClientService readClientService;

    @Autowired
    private UpdateClientService updateClientService;

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllClients(){
        try {
            List<ClientDTO> dtoList = readClientService.getAllClients();
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-by")
    public ResponseEntity<Object> getClient(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String phone) {
        try {
            ClientDTO dto;
            if (email != null) {
                dto = readClientService.getClientByEmail(email);
            } else if (cpf != null) {
                dto = readClientService.getClientByCpf(cpf);
            } else if (phone != null) {
                dto = readClientService.getClientByPhone(phone);
            } else {
                throw new IllegalArgumentException("Informe ao menos um parâmetro.");
            }
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getClientById(@RequestParam(required = false) UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo.");
        } else {
            try {
                ClientDTO dto = readClientService.getClientById(id);
                return new ResponseEntity<>(dto, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
            }
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateClient(@RequestBody Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        } else {
            try {
                ClientDTO dto = updateClientService.updateCliente(client);
                return new ResponseEntity<>(dto, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createClient(@RequestBody(required = false) Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        } else {
            try {
                ClientDTO dto = createClientService.createCliente(client);
                return new ResponseEntity<>(dto, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteClient(@RequestParam(required = false) UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        } else {
            try {
                deleteClientService.deleteClient(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (RuntimeException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
    }

}
