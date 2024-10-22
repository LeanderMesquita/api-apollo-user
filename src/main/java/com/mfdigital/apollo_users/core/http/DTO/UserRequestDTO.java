package com.mfdigital.apollo_users.core.http.DTO;


import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.Instant;


public record UserRequestDTO(
        @NotBlank(message = "Name cannot be blank")
        @Pattern(regexp = "^[A-Za-z]+$", message = "Please, enter your name correctly.")
        String name,

        @NotBlank(message = "Last name cannot be blank")
        @Pattern(regexp = "^[A-Za-z]+$", message = "Please, enter your last name correctly.")
        String lastName,

        @NotNull
        UserRole role,
        @NotNull
        Sector sector,
        @NotNull
        State state,
        boolean status
) {
}
