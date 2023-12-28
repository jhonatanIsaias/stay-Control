package com.company.account.model.enums;

public enum RolesEnum {
    ADMIN("admin"),
    USER("user");


    private String role;

    RolesEnum(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
