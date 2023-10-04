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

    public void saveClient(Cliente cliente){
        clientRepository.save(cliente);
    }
    public List<Cliente> findAll(){
        List<Cliente> list =  clientRepository.findAll();
        return list;
    }
    public void deleteCliente(Long id){
        clientRepository.deleteById(id);
    }

    public Cliente updateCliente(Long id , Cliente clienteUpdated){
        Cliente obj = clientRepository.getReferenceById(id);
        updateData(obj,clienteUpdated);

        return clientRepository.save(obj);
    }

    private void updateData(Cliente obj, Cliente clienteUpdated) {
        if(clienteUpdated.getName() != null) {
            obj.setName(clienteUpdated.getName());
        }

        if(clienteUpdated.getDayLimit() != null) {
            obj.setDayLimit(clienteUpdated.getDayLimit());
        }

    }
}
