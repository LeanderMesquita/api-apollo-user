package com.mfdigital.apollo_users.core.http.controllers;

import java.util.Optional;

import com.mfdigital.apollo_users.core.docs.AuthDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfdigital.apollo_users.core.entity.User;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import com.mfdigital.apollo_users.core.http.DTO.AuthDTO;
import com.mfdigital.apollo_users.core.http.DTO.LoginResponseDTO;
import com.mfdigital.apollo_users.core.http.DTO.RegisterDTO;
import com.mfdigital.apollo_users.core.http.services.TokenService;
import com.mfdigital.apollo_users.core.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController implements AuthDocs {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO data){

            User user = (User) userRepository.findByEmail(data.email());

            if (user == null){
                throw new AccessDeniedException("Usuário não registrado.");
            }

            if(!user.isEnabled()){
                throw new AccessDeniedException("Usuário inativo.");
            }

            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());

            LoginResponseDTO response = new LoginResponseDTO(
                    token,
                    user.getUsername(),
                    user.getEmail(),
                    user.getUserRole(),
                    user.getSector(),
                    user.getState());

            return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data){
        var email = data.email();
        if(this.userRepository.findByEmail(email) != null){
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        Optional<User> existingUserWithAdminRole = userRepository.findByUserRole(UserRole.ADMIN);

        if (existingUserWithAdminRole.isPresent() && data.role().toString().equals("ADMIN")) {
            throw new AccessDeniedException("Não permitido.");
        }

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
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
