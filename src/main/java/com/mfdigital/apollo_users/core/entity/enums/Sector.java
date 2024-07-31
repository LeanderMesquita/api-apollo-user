package com.mfdigital.apollo_users.core.entity.enums;


import lombok.Getter;

@Getter
public enum Sector {
    TRIAGE("triage"),
    REGISTER("register"),
    FULFILLMENT("fulfillment"),
    QUALITY("quality");

    private final String sector;

    Sector(String sector){this.sector = sector;}
}
