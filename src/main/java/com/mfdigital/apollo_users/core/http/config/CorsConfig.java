package com.mfdigital.apollo_users.core.http.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@Slf4j
public class CorsConfig {

    @Value("${allowed.ip1}")
    private String allowedIp1;
    @Value("${allowed.ip2}")
    private String allowedIp2;
    @Value("${SPRING_PROFILES_ACTIVE}")
    private String profile;

    @Bean
    public CorsFilter corsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        log.info(allowedIp1);
        log.info(allowedIp2);
        log.info(profile);

        config.setAllowCredentials(true);
        if(profile.trim().equals("homolog")){
            config.addAllowedOriginPattern("*");
        }
        config.addAllowedOrigin(allowedIp1.trim());
        config.addAllowedOrigin(allowedIp2.trim());
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
