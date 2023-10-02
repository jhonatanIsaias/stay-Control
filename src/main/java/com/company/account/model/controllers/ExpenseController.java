package com.company.account.model.controllers;


import com.company.account.model.entities.Expense;
import com.company.account.model.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<Expense>> findAll(){
        List<Expense> list = expenseService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "{ìd}")
    public ResponseEntity<Expense> findById(@PathVariable Long id){
      Expense obj =   expenseService.findById(id);
        return ResponseEntity.ok().body(obj);
    }


}
