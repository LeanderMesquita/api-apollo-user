package com.mfdigital.apollo_users.core.http.controllers;

import com.mfdigital.apollo_users.core.docs.UserDocs;
import com.mfdigital.apollo_users.core.entity.User;
import com.mfdigital.apollo_users.core.http.DTO.UserRequestDTO;
import com.mfdigital.apollo_users.core.http.DTO.UserResponseDTO;
import com.mfdigital.apollo_users.core.http.DTO.UserStatusRequestDTO;
import com.mfdigital.apollo_users.core.http.services.UserService;
import com.mfdigital.apollo_users.core.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController implements UserDocs {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserResponseDTO> userResponseDTOs = users.stream()
                .map(UserResponseDTO::new)
                .toList();

        return new ResponseEntity<>(userResponseDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) {

        return userRepository.findById(id).map(user -> new ResponseEntity<>(new UserResponseDTO(user), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateProfile(@PathVariable String id, @RequestBody @Valid UserRequestDTO request) {
        User updatedUser = userService.updateUser(id, request);
        return new ResponseEntity<>(new UserResponseDTO(updatedUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inactivateUser (@PathVariable String id, @RequestBody @Valid UserStatusRequestDTO request) {
        User user = userService.inactivateUser(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
