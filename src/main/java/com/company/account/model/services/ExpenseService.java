package com.company.account.model.services;



import com.company.account.model.entities.Expense;
import com.company.account.model.enums.ExpenseStatus;

import com.company.account.model.repositories.ExpenseRepository;
import com.company.account.model.services.Exceptions.DayLimitException;
import com.company.account.model.services.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepository  expenseRepository;


    public void saveExpense(Expense expense){
        LocalDate today = LocalDate.now();
        List<Expense> listExpense = findExpensesByIdClient(expense.getCliente().getId());
        updateStatus();
        for(Expense e : listExpense){
            if(e.getExpenseStatus() == ExpenseStatus.ATRASADO){
                throw new DayLimitException();
            }
        }
        expenseRepository.save(expense);
    }
    private List<Expense> findExpensesByIdClient(long clientID){
     List<Expense> list = expenseRepository.findByClientId(clientID);
     return  list;
    }
    private void updateStatus() {
        List<Expense> expenses = expenseRepository.findAll();
        LocalDate today = LocalDate.now();
        for(Expense e : expenses){

            long time = ChronoUnit.DAYS.between(e.getDateLimit(),today);
            if(time > e.getCliente().getDayLimit()){

               e.setExpenseStatus(ExpenseStatus.ATRASADO);
            }
        }
    }
    public List<Expense> findAll(){
        updateStatus();
       List<Expense> list =  expenseRepository.findAll();
       return list;
    }

    public Expense findById(long id){
        updateStatus();
       Optional<Expense> obj = expenseRepository.findById(id);
       return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    public void deleteExpense(Long id){
        try{
            expenseRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
    }

}
