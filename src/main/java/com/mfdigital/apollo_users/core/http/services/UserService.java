package com.mfdigital.apollo_users.core.http.services;

import com.mfdigital.apollo_users.core.entity.User;
import com.mfdigital.apollo_users.core.http.DTO.UserRequestDTO;
import com.mfdigital.apollo_users.core.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private ValidatorService validatorService;

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User updateUser(String id, UserRequestDTO userDetails) {

        User authUserWhoRequestingChange = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        var roleAuthUser = authUserWhoRequestingChange.getUserRole().toString();
        var roleUpdateUser = userToUpdate.getUserRole().toString();

        var sectorAuthUser = authUserWhoRequestingChange.getSector().toString();
        var sectorUpdateUser = userToUpdate.getSector().toString();

        validatorService.roleValidate(roleUpdateUser, roleAuthUser);
        validatorService.sectorValidate(sectorUpdateUser, sectorAuthUser);

        userToUpdate.setUsername(userDetails.name()+ " "+userDetails.lastName());
        userToUpdate.setEmail(userDetails.email());
        userToUpdate.setUserRole(userDetails.role());

        return userRepository.save(userToUpdate);
    }

    public User inactivateUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        user.setStatus(false);
        return userRepository.save(user);
    }
}
