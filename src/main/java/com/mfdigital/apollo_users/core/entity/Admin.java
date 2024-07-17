package com.mfdigital.apollo_users.core.entity;


import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Admin")
public class Admin extends User{

    @Id
    private final String idAdmin;

    public Admin(String name, String lastName, Sector sector, State state, UserRole userRole, Boolean isActive, String email, String password) {
        super(name, lastName, sector, state, userRole, isActive, email, password);
        this.idAdmin = super.id;
    }

    public String getIdAdmin(){
        return idAdmin;
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