package com.company.account.model.services;


import com.company.account.model.entities.Expense;
import com.company.account.model.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepository  expenseRepository;

    public void saveExpense(Expense expense){
            expenseRepository.save(expense);
    }
    public List<Expense> findAll(){
       List<Expense> list =  expenseRepository.findAll();
       return list;
    }

    public Expense findById(long id){
       Optional<Expense> obj = expenseRepository.findById(id);
       return obj.get();
    }
}
