package com.mfdigital.apollo_users.core.entity;


import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "Admin")
public class Admin extends User{

    public Admin(String name, String lastName, Sector sector, State state, Boolean isActive, String email, String password) {
        super(name, lastName, sector, state, isActive, email, password);
    }

    @Override
    public Collection<GrantedAuthority> getAutorization() {
        return List.of();
    }

    @Override
    public String getUserName() {
        return name+" "+lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Sector getSector() {
        return sector;
    }

    @Override
    public State getState() {
        return state;
    }
}