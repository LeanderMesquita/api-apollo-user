package com.mfdigital.apollo_users.core.docs;

import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import com.mfdigital.apollo_users.core.http.DTO.UserRequestDTO;
import com.mfdigital.apollo_users.core.http.DTO.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Usuários")
public interface UserDocs {

    @Operation(summary = "Retorna todos os usuários.", security = {@SecurityRequirement(name = "bearerAuth")})
    ResponseEntity<Page<UserResponseDTO>> getAllUsers
            (
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "25") int size,
                    @RequestParam(required = false) Sector sector,
                    @RequestParam(required = false) State state,
                    @RequestParam(required = false) UserRole role,
                    @RequestParam(required = false) String username
            );

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
                                    value = """
                                            {
                                                "name": "Pedro",
                                                "lastName": "Gustavo",
                                                "role": "COORDENADOR",
                                                "sector": "TRIAGEM",
                                                "status": true,
                                                "state": "CE"
                                            }
                                            """
                            )
                    }
            )
    )
    ResponseEntity<UserResponseDTO> updateProfile(@PathVariable String id, @RequestBody @Valid UserRequestDTO request);

}
