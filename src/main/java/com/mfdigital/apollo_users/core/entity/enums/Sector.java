package com.mfdigital.apollo_users.core.entity.enums;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Sector")
public enum Sector {
    TRIAGE("triage"),
    REGISTER("register"),
    FULLFILLMENT("fullfillment"),
    QUALITY("quality");

    @Id
    private UUID idSector;

    private final String sector;

    Sector(String sector){
        this.sector = sector;
    }
}
