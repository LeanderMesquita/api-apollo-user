package com.mfdigital.apollo_users.core.http.DTO;

import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;

public record LoginResponseDTO(String token, String username, UserRole role, Sector sector, State state) {
}
