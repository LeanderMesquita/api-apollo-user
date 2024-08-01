package com.mfdigital.apollo_users.core.http.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/view").hasAnyRole("ADMIN", "COORDINATOR", "SUPERVISOR")
                        .requestMatchers(HttpMethod.GET, "/users/profile/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/users/update/{id}").hasAnyRole("ADMIN", "COORDINATOR", "SUPERVISOR")
                        .requestMatchers(HttpMethod.PATCH, "/users/update/{id}").hasAnyRole("ADMIN", "COORDINATOR", "SUPERVISOR")
                        .anyRequest().authenticated()
                ).build();

    }
}
