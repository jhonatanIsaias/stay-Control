package com.company.account.model.entities;


import com.company.account.model.enums.RolesEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
@Entity
public class Company implements Serializable,UserDetails {
    private String name;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String email;
    private RolesEnum role;
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private Set<Cliente> clientes = new HashSet<>();



    public Company(){

    }

    public Company(String name) {
        this.name = name;
    }

    public Company(Long id, String name, String password, String email,RolesEnum role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = id;
        this.role = role;
    }
    public Company( String name, String password, String email,RolesEnum role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       if(this.role == RolesEnum.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
       else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
