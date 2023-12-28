package com.company.account.model.repositories;


import com.company.account.model.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
     List<Expense> findByClienteId(long cliente);
}
