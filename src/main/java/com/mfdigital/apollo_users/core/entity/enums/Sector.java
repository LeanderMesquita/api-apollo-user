package com.mfdigital.apollo_users.core.entity.enums;

import java.util.UUID;

import org.springframework.data.annotation.Id;

public enum Sector {
    TRIAGE("triage"),
    REGISTER("register"),
    FULLFILLMENT("fullfillment"),
    QUALITY("quality");

    @Id
    private UUID id_sector;

    private final String sector;

    Sector(String sector){
        this.sector = sector;
    }
}
