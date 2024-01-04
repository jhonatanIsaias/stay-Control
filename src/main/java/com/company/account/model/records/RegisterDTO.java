package com.company.account.model.records;

import com.company.account.model.enums.RolesEnum;

public record RegisterDTO(String name ,String email, String password, int role){
}
