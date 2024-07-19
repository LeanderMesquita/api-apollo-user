package com.mfdigital.apollo_users.core.entity.enums;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "State")
public enum State {
    CE("CE", "Ceará"),
    RJ("RJ", "Rio de Janeiro"),
    SP("SP", "São Paulo");

    @Id
    private UUID idState;

    private final String uf;
    private final String description;

    State(String uf, String description){
        this.uf = uf;
        this.description = description;
    }
}