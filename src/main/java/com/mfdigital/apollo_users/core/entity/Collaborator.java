package com.mfdigital.apollo_users.core.entity;

import com.mfdigital.apollo_users.core.entity.enums.State;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public class Collaborator extends User {
    private Timestamp tma;

    public Collaborator(Timestamp tma) {
        super();
        this.tma = tma;
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
        return "";
    }

    @Override
    public String getEmail() {
        return "";
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public Sector getSector() {
        return null;
    }

    @Override
    public State getState() {
        return null;
    }
}
