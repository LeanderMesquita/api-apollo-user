package com.mfdigital.apollo_users.core.entity;


import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "users")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = "id")
@Getter
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Setter
    private String username;
    @Setter
    private String email;
    private String password;

    @Setter
    private Boolean status;
    @Setter
    private Instant tma;

    @Setter
    private UserRole userRole;
    private State state;
    private Sector sector;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant created_at;

    @Column(nullable = false)
    private Instant updated_at;

    public User (
            String name,
            String lastName,
            String email,
            String password,
            UserRole userRole,
            State state,
            Sector sector
    ){
        this.username = name+" "+lastName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.state = state;
        this.sector = sector;
        this.status = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  switch (this.userRole){
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_COORDINATOR"),
                    new SimpleGrantedAuthority("ROLE_SUPERVISOR"),
                    new SimpleGrantedAuthority("ROLE_COLLABORATOR")
            );
            case COORDINATOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_COORDINATOR"),
                    new SimpleGrantedAuthority("ROLE_SUPERVISOR"),
                    new SimpleGrantedAuthority("ROLE_COLLABORATOR")
            );
            case SUPERVISOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_SUPERVISOR"),
                    new SimpleGrantedAuthority("ROLE_COLLABORATOR")
            );
            case COLLABORATOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_COLLABORATOR")
            );
        };

    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
