package com.company.account.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.apache.el.stream.Stream;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Cliente implements Serializable {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @Column(name = "expenses")
    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Expense> expenses = new ArrayList<>();
    private Integer dayLimit;
    public Cliente() {
    }

    public Cliente(String name, Long id, Company company,Integer dayLimit) {
        this.name = name;
        this.id = id;
        this.company = company;
        this.dayLimit = dayLimit;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public Integer getDayLimit() {
       return  this.dayLimit;
    }

    public void setDayLimit(Integer dayLimit) {
        this.dayLimit = dayLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return Objects.equals(getId(), cliente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Double TotalExpenses(){
        double sum = 0.0;
        for(Expense expense : expenses){
            sum += expense.getValue();
        }
        return sum;
    }
}
