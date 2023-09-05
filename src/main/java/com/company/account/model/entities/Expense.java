package com.company.account.model.entities;

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
    @JoinColumn(name = "company_id")
    private Company company;
    private String demandCompany;

    private Double value;

    private LocalDate dateLimit;

    public Expense(Long id, String demandCompany, Double value, LocalDate dateLimit) {
        this.id = id;
        this.demandCompany= demandCompany;
        this.value = value;
        this.dateLimit = dateLimit;
    }

    public Long getId() {
        return id;
    }

    public Expense() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getdemandCompany() {
        return demandCompany;
    }

    public void setdemandCompany(String demandCompany) {
        this.demandCompany= demandCompany;
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
        this.dateLimit = dateLimit;
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
