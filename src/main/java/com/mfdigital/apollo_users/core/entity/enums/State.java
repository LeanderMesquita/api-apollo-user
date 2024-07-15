package com.mfdigital.apollo_users.core.entity.enums;

public enum State {
    CE("CE", "Ceará"),
    RJ("RJ", "Rio de Janeiro"),
    SP("SP", "São Paulo");

    private final String uf;
    private final String description;

    State(String uf, String description){
        this.uf = uf;
        this.description = description;
    }

    public String getUf(){
        return uf;
    }
    public String getDescription(){
        return description;
    }

}