package com.company.account.model.services;

import com.company.account.model.entities.Cliente;
import com.company.account.model.repositories.ClientRepository;
import com.company.account.model.services.Exceptions.DataBaseException;
import com.company.account.model.services.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public Cliente findById(long id){
        Optional<Cliente> client = clientRepository.findById(id);
        return client.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    public void deleteCliente(Long id){
        try{
            clientRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }

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
