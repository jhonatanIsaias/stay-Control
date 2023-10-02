package com.company.account.model.services;

import com.company.account.model.entities.Cliente;
import com.company.account.model.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public void saveCompany(Cliente cliente){
        clientRepository.save(cliente);
    }
    public List<Cliente> findAll(){
        List<Cliente> list =  clientRepository.findAll();
        return list;
    }
}
