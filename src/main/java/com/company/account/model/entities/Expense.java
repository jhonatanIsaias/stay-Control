package com.company.account.model.entities;

import com.company.account.model.enums.ExpenseStatus;
import jakarta.persistence.*;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class Expense implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Cliente cliente;

   DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Double value;

    private Integer expenseStatus;
    private LocalDate dateLimit;



    public Expense(Long id, Double value, LocalDate date, ExpenseStatus expenseStatus) {
        this.id = id;
        this.value = value;
        setExpenseStatus(expenseStatus);
        this.dateLimit = LocalDate.parse(dateTimeFormatter.format(dateLimit));
    }

    public Long getId() {
        return id;
    }

    public Expense() {
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDate getDateLimit() {
        return dateLimit;
    }

    public void setDateLimit(LocalDate dateLimit) {
        this.dateLimit = LocalDate.parse(dateTimeFormatter.format(dateLimit));
    }

    public ExpenseStatus getExpenseStatus() {
        return ExpenseStatus.valueOf(expenseStatus);
    }

    public void setExpenseStatus(ExpenseStatus expenseStatus) {
        if(expenseStatus != null) {
            this.expenseStatus = expenseStatus.getCode();
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expense expense)) return false;
        return Objects.equals(getId(), expense.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
