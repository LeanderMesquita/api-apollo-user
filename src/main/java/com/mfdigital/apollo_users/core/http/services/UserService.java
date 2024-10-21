package com.mfdigital.apollo_users.core.http.services;

import com.mfdigital.apollo_users.core.entity.User;
import com.mfdigital.apollo_users.core.http.DTO.UserRequestDTO;
import com.mfdigital.apollo_users.core.http.DTO.UserStatusRequestDTO;
import com.mfdigital.apollo_users.core.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
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


        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        User authUserWhoRequestingChange = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var roleAuthUser = authUserWhoRequestingChange.getUserRole().toString();
        var sectorAuthUser = authUserWhoRequestingChange.getSector().toString();
        var stateAuthUser = authUserWhoRequestingChange.getState().toString();

        var roleUpdateUser = userToUpdate.getUserRole().toString();
        var sectorUpdateUser = userToUpdate.getSector().toString();
        var stateUpdateUser = userToUpdate.getState().toString();

        validatorService.roleValidate(roleUpdateUser, roleAuthUser);
        validatorService.sectorValidate(sectorUpdateUser, sectorAuthUser, roleAuthUser);
        validatorService.stateValidate(stateUpdateUser, stateAuthUser, roleAuthUser);

        userToUpdate.setUsername(userDetails.name()+ " "+userDetails.lastName());
        userToUpdate.setUserRole(userDetails.role());
        userToUpdate.setState(userDetails.state());
        userToUpdate.setSector(userDetails.sector());
        userToUpdate.setStatus(userDetails.status());

        return userRepository.save(userToUpdate);
    }

    public User inactivateUser(String id, UserStatusRequestDTO userDetails) {

        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        User authUserWhoRequestingChange = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var roleAuthUser = authUserWhoRequestingChange.getUserRole().toString();
        var sectorAuthUser = authUserWhoRequestingChange.getSector().toString();
        var stateAuthUser = authUserWhoRequestingChange.getState().toString();

        var roleUpdateUser = userToUpdate.getUserRole().toString();
        var sectorUpdateUser = userToUpdate.getSector().toString();
        var stateUpdateUser = userToUpdate.getState().toString();

        validatorService.roleValidate(roleUpdateUser, roleAuthUser);
        validatorService.sectorValidate(sectorUpdateUser, sectorAuthUser, roleAuthUser);
        validatorService.stateValidate(stateUpdateUser, stateAuthUser, roleAuthUser);

        userToUpdate.setStatus(userDetails.status());
        return userRepository.save(userToUpdate);
    }
}
