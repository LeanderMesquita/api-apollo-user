package com.mfdigital.apollo_users.core.repositories.seeds;

import com.github.javafaker.Faker;
import com.mfdigital.apollo_users.core.entity.User;
import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import com.mfdigital.apollo_users.core.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile({"dev"})
public class DataSeeder {

    @Bean
    @Transactional
    public CommandLineRunner seeder(UserRepository userRepository){
        return args -> {
            List<User> usersMock = new ArrayList<>();
            Faker faker = new Faker();

            if(userRepository.findByEmail("advprojetos@meirelesefreitas.com.br") == null){
                User admin = new User(
                        "Admin",
                        "Projetos",
                        "advprojetos@meirelesefreitas.com.br",
                        new BCryptPasswordEncoder().encode("Admin@projetos2025"),
                        UserRole.ADMIN,
                        State.CE,
                        Sector.TRIAGEM
                );

                admin.setStatus(true);
                usersMock.add(admin);
            }

            for (int i = 0; i < 100 ; i++){

                String email = faker.name().username() + "@meirelesefreitas.adv.br";
                User user = new User(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        email,
                        new BCryptPasswordEncoder().encode("Mock@users123"),
                        UserRole.COLABORADOR,
                        State.values()[faker.number().numberBetween(0, State.values().length)],
                        Sector.values()[faker.number().numberBetween(0, Sector.values().length)]
                );
                usersMock.add(user);
            }

            userRepository.saveAll(usersMock);

        };
    }
}
