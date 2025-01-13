package com.mfdigital.apollo_users.core.docs;

import com.mfdigital.apollo_users.core.http.DTO.AuthDTO;
import com.mfdigital.apollo_users.core.http.DTO.RegisterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Autenticação")
public interface AuthDocs {


    @Operation(summary = "Autenticação e login")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Login de usuário",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(
                                    name = "Exemplo de login",
                                    summary = "Exemplo de login de usuário",
                                    description = "Exemplo de um corpo de requisição para registrar um usuário",
                                    value = """
                                            {
                                                "email": "joaogomes@meirelesefreitas.adv.br",
                                                "password": "Joaozinho123!#"
                                            }
                                            """
                            )
                    }
            )
    )
    ResponseEntity<?> login(@RequestBody @Valid AuthDTO data);

    @Operation(summary = "Registro de usuário")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados de registro do usuário",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(
                                    name = "Exemplo de registro",
                                    summary = "Exemplo de register de usuário",
                                    description = "Exemplo de um corpo de requisição para registrar um usuário.",
                                    value = """
                                            {
                                                 "name": "Pedro",
                                                 "lastName": "santana",
                                                 "email": "teste@meirelesefreitas.adv.br",
                                                 "password": "Teste@123",
                                                 "role": "COLABORADOR",
                                                 "sector": "TRIAGEM",
                                                 "state": "CE"
                                            }
                                            """
                            )
                    }
            )
    )
    ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data);

}
