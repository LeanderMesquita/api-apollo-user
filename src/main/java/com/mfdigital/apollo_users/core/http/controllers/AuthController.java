package com.mfdigital.apollo_users.core.http.controllers;

import com.mfdigital.apollo_users.core.entity.User;
import com.mfdigital.apollo_users.core.http.DTO.AuthDTO;
import com.mfdigital.apollo_users.core.http.DTO.LoginDTO;
import com.mfdigital.apollo_users.core.http.DTO.RegisterDTO;
import com.mfdigital.apollo_users.core.http.services.TokenService;
import com.mfdigital.apollo_users.core.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDTO data){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new LoginDTO(token));
        } catch (AuthenticationException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect or invalid credentials.");
        }

    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data){
        var email = data.email();
        if(this.userRepository.findByEmail(email) != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with email " + email + " already exists");
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User(
                data.name(),
                data.lastName(),
                data.email(),
                encryptedPassword,
                data.role(),
                data.state(),
                data.sector()
        );

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

}
