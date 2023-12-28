package com.company.account.model.entities;

import com.company.account.model.enums.ExpenseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Expense implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Cliente cliente;
    @Column
    private Float value;
    @Column
    private Integer expenseStatus;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;




    public Expense(Long id, Cliente cliente, Float value, LocalDate date, ExpenseStatus expenseStatus) {
        this.id = id;
        this.cliente = cliente;
        this.value = value;
        setExpenseStatus(expenseStatus);
        this.date = date;

    }
    public Expense() {
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDateLimit(LocalDate dateLimit) {
        this.date = dateLimit;
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
