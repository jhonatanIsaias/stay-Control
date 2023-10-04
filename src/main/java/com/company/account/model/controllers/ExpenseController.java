package com.company.account.model.controllers;

import com.company.account.model.entities.Expense;
import com.company.account.model.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;
    @PostMapping
    public ResponseEntity<Expense> save(@RequestBody Expense expense){
       expenseService.saveExpense(expense);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(expense.getId()).toUri();
        return  ResponseEntity.created(uri).body(expense);
    }

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

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();

    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id ,@RequestBody Expense expense){
        expenseService.updateExpense(id,expense);

        return ResponseEntity.ok().body(expense);

    }


}
