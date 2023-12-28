package com.company.account.model.repositories;

import com.company.account.model.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface CompanyRepository extends JpaRepository<Company,Long> {
   UserDetails findByEmail(String email);
}
