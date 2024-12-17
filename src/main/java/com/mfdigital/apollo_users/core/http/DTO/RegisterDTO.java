package com.mfdigital.apollo_users.core.http.DTO;

import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record RegisterDTO(
        @NotBlank(message = "Nome não pode ser vazio.")
        String name,

        @NotBlank(message = "Sobrenome não pode ser vazio.")
        String lastName,

        @NotBlank(message = "Email não pode ser vazio.")
        @Email
        @Pattern(regexp = "^[A-Za-z0-9.]+@meirelesefreitas\\.adv\\.br$", message = "Email inválido, Entre em contato com suporte.")
        String email,

        @NotBlank(message = "Campo senha não pode ser vazio.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%¨|&*()_+=;:?.^])[A-Za-z\\d!@#$%¨|&*()_+=;:?.^]{8,}$",
                message = "A senha deve conter pelo menos um caractere minúsculo, um caractere maiúsculo, um caractere especial, um número e deve ter pelo menos 8 caracteres."
        )
        String password,

        @NotNull(message = "Permissão não pode ser nula.")
        UserRole role,

        @NotNull(message = "Setor não pode ser nulo.")
        Sector sector,

        @NotNull(message = "Estado não pode ser nulo.")
        State state
) {
}
