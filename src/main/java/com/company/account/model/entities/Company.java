package com.company.account.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
public class Company implements Serializable {
    private String name;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private Set<Cliente> clientes = new HashSet<>();

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public Company(){

    }

    public Company(String name) {
        this.name = name;
    }

    public Company(Long id, String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company company)) return false;
        return Objects.equals(getName(), company.getName()) && Objects.equals(getId(), company.getId()) && Objects.equals(getPassword(), company.getPassword()) && Objects.equals(getEmail(), company.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId(), getPassword(), getEmail());
    }
}
