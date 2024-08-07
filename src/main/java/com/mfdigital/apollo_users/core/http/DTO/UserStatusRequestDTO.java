package com.mfdigital.apollo_users.core.http.DTO;

import jakarta.validation.constraints.NotNull;

public record UserStatusRequestDTO(@NotNull Boolean status) {
}
