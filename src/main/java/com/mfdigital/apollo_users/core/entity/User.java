package com.mfdigital.apollo_users.core.entity;

import com.mfdigital.apollo_users.core.interfaces.UserInterface;
import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;

import java.util.UUID;
import java.time.Instant;

public abstract class User implements UserInterface {
    protected String id;
    protected String name;
    protected String lastName;
    protected Sector sector;
    protected State state;
    protected UserRole userRole;
    protected Boolean isActive;
    protected String email;
    protected String password;
    protected Instant createdAt;
    protected Instant updatedAt;
    
    public User(String name, 
                String lastName, 
                Sector sector, 
                State state, 
                UserRole userRole,
                Boolean isActive, 
                String email, 
                String password) {

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.lastName = lastName;
        this.sector = sector;
        this.state = state;
        this.userRole = userRole;
        this.isActive = isActive;
        this.email = email;
        this.password = password;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
