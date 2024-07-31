package com.mfdigital.apollo_users.core.entity.enums;


import lombok.Getter;

@Getter
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
}
