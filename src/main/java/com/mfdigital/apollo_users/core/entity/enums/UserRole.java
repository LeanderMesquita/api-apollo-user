package com.mfdigital.apollo_users.core.entity.enums;

public enum UserRole {
    COLLABORATOR ("colaborador"),
    COORDINATOR ("coordenador"),
    SUPERVISOR("supervisor"),
    ADMIN ("administrador");

    private final String type;
    UserRole(String type) {this.type = type;}

    public String getType() {return type;}
}
