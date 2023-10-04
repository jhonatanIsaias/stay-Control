package com.company.account.model.controllers;

import com.company.account.model.entities.Cliente;
import com.company.account.model.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    ClientService clienteService;
    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
        clienteService.saveClient(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();
        return  ResponseEntity.created(uri).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> list = clienteService.findAll();
        return ResponseEntity.ok().body(list);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
       clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();

    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> updateExpense(@PathVariable Long id , @RequestBody Cliente cliente){
        clienteService.updateCliente(id,cliente);

        return ResponseEntity.ok().body(cliente);

     }
    }

