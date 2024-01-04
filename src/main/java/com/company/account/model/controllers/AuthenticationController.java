package com.company.account.model.controllers;

import com.company.account.model.entities.Company;
import com.company.account.model.records.AutheticationDTO;
import com.company.account.model.records.RegisterDTO;
import com.company.account.model.security.TokenService;
import com.company.account.model.services.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;


@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CompanyService companyService;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
        public ResponseEntity authetication(@RequestBody @Valid AutheticationDTO data){

            var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        System.out.println("estou indo autenticar ");
            var auth = this.authenticationManager.authenticate(userNamePassword);
            System.out.println("autentiquei");

            var token = tokenService.generateToken((Company) auth.getPrincipal());

            return ResponseEntity.ok(token);

        }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(companyService.findByEmail(data.email()) != null){
           return ResponseEntity.badRequest().build();
        }
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            Company company = new Company(data.name(),encryptedPassword, data.email(),data.role());

            companyService.saveCompany(company);


        return  ResponseEntity.ok().build();
    }
}
