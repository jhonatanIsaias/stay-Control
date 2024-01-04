package com.company.account.model.enums;

public enum RolesEnum {
    ADMIN(1),
    USER(2);


    private int code;

    RolesEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static RolesEnum valueOf(int code) {
        for (RolesEnum value : RolesEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("role não encontrado");
    }

}
