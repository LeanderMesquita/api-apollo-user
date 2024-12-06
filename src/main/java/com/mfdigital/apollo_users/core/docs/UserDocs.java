package com.mfdigital.apollo_users.core.docs;

import com.mfdigital.apollo_users.core.http.DTO.UserRequestDTO;
import com.mfdigital.apollo_users.core.http.DTO.UserResponseDTO;
import com.mfdigital.apollo_users.core.http.DTO.UserStatusRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Usuários")
public interface UserDocs {

    @Operation(summary = "Retorna todos os usuários.", security = {@SecurityRequirement(name = "bearerAuth")})
    ResponseEntity<List<UserResponseDTO>> getAllUsers();

    @Operation(summary = "Retorna o usuário procurado pelo ID.", security = {@SecurityRequirement(name = "bearerAuth")})
    ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id);

    @Operation(summary = "Atualiza os atributos de usuário.", security = {@SecurityRequirement(name = "bearerAuth")})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados de atualização do usuário",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(
                                    name = "Exemplo de atualização",
                                    summary = "Exemplo de update de usuário",
                                    description = "Exemplo de um corpo de requisição para atualizar um usuário.",
                                    value = "{\"name\": \"João\", \"lastName\": \"Silva\", \"role\": \"COLLABORATOR\", \"sector\": \"TRIAGE\", \"state\": \"CE\", \"status\": true}"
                            )
                    }
            )
    )
    ResponseEntity<UserResponseDTO> updateProfile(@PathVariable String id, @RequestBody @Valid UserRequestDTO request);

    @Operation(summary = "Inativa um usuário baseado pelo ID (Depreciado)", security = {@SecurityRequirement(name = "bearerAuth")})
    ResponseEntity<?> inactivateUser (@PathVariable String id, @RequestBody @Valid UserStatusRequestDTO request);
}
