package com.mfdigital.apollo_users.core.http.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer cors() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedHeaders(HttpHeaders.CONTENT_TYPE, "X-CSRF-TOKEN")
                        .allowedMethods(
                                HttpMethod.GET.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.PATCH.name(),
                                HttpMethod.PUT.name(),
                                HttpMethod.DELETE.name())
                        .allowCredentials(true);
            }
        };
    }
}
