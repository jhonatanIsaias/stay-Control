package com.company.account.model.repositories;

import com.company.account.model.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Cliente,Long> {

}
