package com.mfdigital.apollo_users.core.http.DTO;

import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;

public record LoginResponseRabbitmqDTO(
    String username,
    Sector sector,
    State state
) {}
