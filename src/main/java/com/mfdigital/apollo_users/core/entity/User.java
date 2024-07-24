package com.mfdigital.apollo_users.core.entity;

import com.mfdigital.apollo_users.core.interfaces.UserInterface;
import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.time.Instant;

@MappedSuperclass
public class User implements UserInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;
    @Column(nullable = false)
    protected String name;
    @Column(nullable = false)
    protected String lastName;
    @Enumerated(EnumType.STRING)
    protected Sector sector;
    @Enumerated(EnumType.STRING)
    protected State state;
    protected UserRole userRole;
    @Column(nullable = false)
    protected Boolean isActive;
    @Column(nullable = false)
    protected String email;
    @Column(nullable = false)
    protected String password;
    @Column(nullable = false)
    protected Instant createdAt;
    @Column(nullable = false)
    protected Instant updatedAt;
    
    public User(String name, 
                String lastName, 
                Sector sector, 
                State state, 
                Boolean isActive,
                String email, 
                String password) {

        this.id = UUID.randomUUID();
        this.name = name;
        this.lastName = lastName;
        this.sector = sector;
        this.state = state;
        this.isActive = isActive;
        this.email = email;
        this.password = password;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    @Override
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public Collection<GrantedAuthority> getAutorization() {
        return List.of();
    }

    @Override
    public String getUserName() {
        return getName() + " " + getLastName();
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
