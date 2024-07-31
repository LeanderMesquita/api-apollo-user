package com.mfdigital.apollo_users.core.entity.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    COLLABORATOR ("collaborator"),
    COORDINATOR ("coordinator"),
    SUPERVISOR("supervisor"),
    ADMIN ("administrator");

    private final String role;

    UserRole(String role){this.role = role;}

}
