package com.company.account.model.services;

import com.company.account.model.entities.Company;
import com.company.account.model.repositories.CompanyRepository;
import com.company.account.model.services.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public void saveCompany(Company company){
            companyRepository.save(company);
    }
    public List<Company> findAll(){
       List<Company> list =  companyRepository.findAll();
       return list;
    }
    public void deleteCompany(Long id){
        try{
            companyRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
    }
    public UserDetails findByEmail(String email){
       return companyRepository.findByEmail(email);
    }
}
