package com.mfdigital.apollo_users.core.entity.enums;


import lombok.Getter;

@Getter
public enum Sector {
    TRIAGEM("Triagem"),
    CADASTRO("Cadastro"),
    CUMPRIMENTO("Cumprimento"),
    QUALIDADE("Qualidade");

    private final String sector;

    Sector(String sector){this.sector = sector;}
}
