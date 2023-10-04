package com.company.account.model.controllers;


import com.company.account.model.entities.Company;
import com.company.account.model.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {
    @Autowired
CompanyService companyService;
    @PostMapping
    public ResponseEntity<Company> save(@RequestBody Company company){
      companyService.saveCompany(company);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(company.getId()).toUri();
        return  ResponseEntity.created(uri).body(company);
    }
    @GetMapping
    public ResponseEntity<List<Company>> findAll(){
        List<Company> list = companyService.findAll();
        return ResponseEntity.ok().body(list);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();

    }


}
