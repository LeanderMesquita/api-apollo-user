package com.mfdigital.apollo_users.core.interfaces;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;

public interface UserInterface {
    public abstract Collection<GrantedAuthority> getAutorization();
    public abstract String getUserName();
    public abstract String getEmail();
    public abstract String getPassword();
    public abstract Sector getSector();
    public abstract State getState();
}
