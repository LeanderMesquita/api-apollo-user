package com.mfdigital.apollo_users.core.http.DTO;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;

public record LoginResponseDTO(String token, String username, String email, UserRole role, Sector sector, State state) {

    @JsonGetter("role")
    public String getRoleJSON(){
        return role.getRole();
    }

    @JsonGetter("state")
    public String getStateJSON(){
        return state.getUf() + ", " + state.getDescription();
    }

    @JsonGetter("sector")
    public String getSectorJSON(){
        return sector.getSector();
    }
}
