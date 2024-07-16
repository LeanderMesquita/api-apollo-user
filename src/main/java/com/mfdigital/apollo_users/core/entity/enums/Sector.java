package com.mfdigital.apollo_users.core.entity.enums;

public enum Sector {
    TRIAGE("triage"),
    REGISTER("register"),
    GREETING("greeting"),
    QUALITY("quality");

    private final String sector;

    Sector(String sector){
        this.sector = sector;
    }

    public String getSector(){
        return sector;
    }
}
