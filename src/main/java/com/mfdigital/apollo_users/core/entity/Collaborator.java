package com.mfdigital.apollo_users.core.entity;

import lombok.Data;

import java.sql.Timestamp;

public class Collaborator extends UserClass {
    private Timestamp tma;

    public Collaborator(Timestamp tma) {
        this.tma = tma;
    }

    public Timestamp getTma() {
        return tma;
    }

    public void setTma(Timestamp tma) {
        this.tma = tma;
    }
}
