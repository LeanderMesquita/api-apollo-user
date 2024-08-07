package com.mfdigital.apollo_users.core.http.DTO;

import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record RegisterDTO(
        @NotBlank(message = "Name cannot be blank")
        @Pattern(
                regexp = "^[A-Za-z]+$", message = "Please, enter your name correctly."
        )
        String name,

        @NotBlank(message = "Last name cannot be blank")
        @Pattern(regexp = "^[A-Za-z]+$", message = "Please, enter your last name correctly.")
        String lastName,

        @NotBlank(message = "Email cannot be blank")
        @Email
        @Pattern(regexp = "^[A-Za-z0-9.]+@meirelesefreitas\\.adv\\.br$", message = "Email must be valid and end with '@meirelesefreitas.adv.br'")
        String email,

        @NotBlank(message = "Password cannot be blank")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%¨|&*()_+=;:?.^])[A-Za-z\\d!@#$%¨|&*()_+=;:?.^]{8,}$",
                message = "Password must contain at least one lowercase character, one uppercase character, one special character, one number, and must be at least 8 characters long"
        )
        String password,

        @NotNull(message = "Role cannot be null")
        UserRole role,

        @NotNull(message = "Sector cannot be null")
        Sector sector,

        @NotNull(message = "State cannot be null")
        State state
) {
}
