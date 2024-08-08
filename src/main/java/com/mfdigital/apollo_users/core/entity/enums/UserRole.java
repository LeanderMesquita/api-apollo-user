package com.mfdigital.apollo_users.core.entity.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    COLLABORATOR ("Colaborador"),
    COORDINATOR ("Coordenador"),
    SUPERVISOR("Supervisor"),
    ADMIN ("Administrador");

    private final String role;

    UserRole(String role){this.role = role;}

}
