package com.mfdigital.apollo_users.core.http.DTO;

import com.mfdigital.apollo_users.core.entity.User;
import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;

public record UserResponseDTO(
        String id,
        String username,
        String email,
        UserRole userRole,
        Sector sector,
        State state,
        Boolean status) {

    public UserResponseDTO(User user){
        this(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getUserRole(),
            user.getSector(),
            user.getState(),
            user.getStatus()
        );
    }
}
