package com.mfdigital.apollo_users.core.http.DTO;

import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;

public record LoginResponseRabbitmqDTO(
    String token, 
    String username, 
    UserRole role, 
    Sector sector, 
    State state
) {

    public String getRoleJSON(){
        return role.getRole();
    }

    public String getStateJSON(){
        return state.getUf() + ", " + state.getDescription();
    }

    public String getSectorJSON(){
        return sector.getSector();
    }
}
