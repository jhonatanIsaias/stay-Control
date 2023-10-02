package com.company.account.model.enums;

public enum ExpenseStatus {
    PENDENTE(1),
    PAGO(2),
    ATRASADO(3);

    private int code;

    private ExpenseStatus(int code){
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public static ExpenseStatus valueOf(int code){
        for(ExpenseStatus value :ExpenseStatus.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("status não encontrado");
    }
}
