package com.mfdigital.apollo_users.core.http.controllers;

import com.mfdigital.apollo_users.core.docs.UserDocs;
import com.mfdigital.apollo_users.core.entity.User;
import com.mfdigital.apollo_users.core.entity.enums.Sector;
import com.mfdigital.apollo_users.core.entity.enums.State;
import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import com.mfdigital.apollo_users.core.http.DTO.UserRequestDTO;
import com.mfdigital.apollo_users.core.http.DTO.UserResponseDTO;
import com.mfdigital.apollo_users.core.http.services.UserService;
import com.mfdigital.apollo_users.core.repositories.UserRepository;
import com.mfdigital.apollo_users.core.repositories.specifications.UserSpecification;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController implements UserDocs {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers
    (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size,
            @RequestParam(required = false) Sector sector,
            @RequestParam(required = false) State state,
            @RequestParam(required = false) UserRole role,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Boolean status
    )
    {
        Pageable pageable = PageRequest.of(page, size);
        UserSpecification specification = new UserSpecification(sector, state, role, username, status);


        return new ResponseEntity<>(userService.getAllUsers(specification, pageable).map(UserResponseDTO::new), HttpStatus.OK);
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


}
