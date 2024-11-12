package com.mfdigital.apollo_users.core.http.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Apollo User API")
                        .description("API de controle de autorização e autenticação de usuários.")
                        .version("v1")
                )
                .tags(
                        Arrays.asList(
                                new Tag().name("Usuários").description("Endpoints de controle interno de usuários."),
                                new Tag().name("Autenticação").description("Endpoints de autenticação e registro de usuários.")
                        )
                )
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Autenticação JWT")
                        )
                );
    }

}
