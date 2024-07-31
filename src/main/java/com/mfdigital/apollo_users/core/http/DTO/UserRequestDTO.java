package com.mfdigital.apollo_users.core.http.DTO;


import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.Instant;


public record UserRequestDTO(
        @NotBlank
        @Pattern(regexp = "^[A-Za-z]", message = "Please, enter your name correctly.")
        String name,
        @NotBlank
        @Pattern(regexp = "^[A-Za-z]", message = "Please, enter your last name correctly.")
        String lastName,
        @NotBlank
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@meirelesefreitas\\.adv\\.br$", message = "Email must end with '@meirelesefreitas.adv.br'.")
        String email,
        @NotBlank
        UserRole userRole,
        @NotNull
        Boolean status,
        Instant tma
) {
}
