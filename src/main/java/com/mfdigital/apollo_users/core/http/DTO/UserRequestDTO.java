package com.mfdigital.apollo_users.core.http.DTO;


import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import jakarta.validation.constraints.Email;
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

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email must be valid and end with '@meirelesefreitas.adv.br'")
        @Pattern(regexp = "^[A-Za-z0-9.]+@meirelesefreitas\\.adv\\.br$", message = "Email must end with '@meirelesefreitas.adv.br'.")
        String email,
        @NotNull
        UserRole role,
        @NotNull
        Boolean status,
        Instant tma
) {
}
