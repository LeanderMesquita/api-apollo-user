package com.mfdigital.apollo_users.core.entity;

import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "Supervisor")
public class Supervisor extends User{

    @Id
    private final String id_supervisor;
    private Timestamp tma;

    public Supervisor(String name, String lastName, Sector sector, State state, UserRole userRole, Boolean isActive, String email, String password) {
        super(name, lastName, sector, state, userRole, isActive, email, password);
        this.id_supervisor = super.id;
    }

    public String getId_supervisor() {
        return id_supervisor;
    }

    public Timestamp getTma() {
        return tma;
    }

    public void setTma(Timestamp tma) {
        this.tma = tma;
    }

    @Override
    public Collection<GrantedAuthority> getAutorization() {
        return List.of();
    }

    @Override
    public String getUserName() {
        return name + " " + lastName;
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