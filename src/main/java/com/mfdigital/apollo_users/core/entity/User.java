package com.mfdigital.apollo_users.core.entity;


import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import com.mfdigital.apollo_users.core.http.config.annotations.DisableDate;
import com.mfdigital.apollo_users.core.http.config.listeners.DisableDateListener;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "users")
@EntityListeners({AuditingEntityListener.class, DisableDateListener.class})
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
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    @Setter
    private State state;

    @Enumerated(EnumType.STRING)
    @Setter
    private Sector sector;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    @DisableDate
    private LocalDateTime disabledAt;

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
        this.status = false;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now().minus(3, ChronoUnit.HOURS);
        this.updatedAt = Instant.now().minus(3, ChronoUnit.HOURS);
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now().minus(3, ChronoUnit.HOURS);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  switch (this.userRole){
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_COORDENADOR"),
                    new SimpleGrantedAuthority("ROLE_SUPERVISOR"),
                    new SimpleGrantedAuthority("ROLE_COLABORADOR")
            );
            case COORDENADOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_COORDENADOR"),
                    new SimpleGrantedAuthority("ROLE_SUPERVISOR"),
                    new SimpleGrantedAuthority("ROLE_COLABORADOR")
            );
            case SUPERVISOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_SUPERVISOR"),
                    new SimpleGrantedAuthority("ROLE_COLABORADOR")
            );
            case COLABORADOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_COLABORADOR")
            );
        };

    }

    @Override
    public boolean isEnabled() {
        return status;
    }
}
