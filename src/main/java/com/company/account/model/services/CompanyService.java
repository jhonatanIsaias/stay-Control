package com.company.account.model.services;

import com.company.account.model.entities.Company;
import com.company.account.model.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        companyRepository.deleteById(id);
    }
}
