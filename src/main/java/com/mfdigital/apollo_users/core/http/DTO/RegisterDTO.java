package com.mfdigital.apollo_users.core.http.DTO;

import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterDTO(
        @NotBlank
        @Pattern(regexp = "^[A-Za-z]", message = "Please, enter your name correctly.")
        String name,
        @NotBlank
        @Pattern(regexp = "^[A-Za-z]", message = "Please, enter your last name correctly.")
        String lastName,
        @NotBlank
        @Pattern(regexp = "^[A-Za-z0-9.]+@meirelesefreitas\\.adv\\.br$", message = "Email must end with '@meirelesefreitas.adv.br'.")
        String email,
        @Pattern(regexp = "^[A-Za-z0-9._%+#$&*@!?-]")
        String password,
        @NotBlank
        UserRole role,
        @NotBlank
        Sector sector,
        @NotBlank
        State state
) {
}
