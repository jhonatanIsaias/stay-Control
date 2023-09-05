package com.company.account.model.controllers;

import com.company.account.model.entities.Company;
import com.company.account.model.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "registro")
public class RegisterController {
    @Autowired
    CompanyService companyService;

    @PostMapping
    public ResponseEntity<String> saveCompany(@RequestBody Company company){
        companyService.saveCompany(company);
        return ResponseEntity.ok("empresa salva com sucesso");
    }
}
