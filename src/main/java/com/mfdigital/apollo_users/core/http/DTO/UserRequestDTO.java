package com.mfdigital.apollo_users.core.http.DTO;


import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.Instant;


public record UserRequestDTO(
        @NotBlank(message = "Nome não pode ser vazio.")
        @Pattern(regexp = "^[\\p{L}´`~^¨]+$", message = "Insira seu nome corretamente.")
        String name,

        @NotBlank(message = "Sobrenome não pode ser vazio.")
        @Pattern(regexp = "^[\\p{L}´`~^¨]+$", message = "Insira seu nome corretamente.")
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
