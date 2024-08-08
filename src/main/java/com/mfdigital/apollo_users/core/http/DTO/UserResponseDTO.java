package com.mfdigital.apollo_users.core.http.DTO;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.mfdigital.apollo_users.core.entity.User;
import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;

public record UserResponseDTO(
        String id,
        String username,
        String email,
        String userRole,
        String sector,
        StateInfo state,
        Boolean status) {


    public record StateInfo(String uf, String description){
        public StateInfo(State state){
            this(state.getUf(), state.getDescription());
        }
    }

    public UserResponseDTO(User user){
        this(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getUserRole().getRole(),
            user.getSector().getSector(),
            new StateInfo(user.getState()),
            user.getStatus()
        );
    }
}
