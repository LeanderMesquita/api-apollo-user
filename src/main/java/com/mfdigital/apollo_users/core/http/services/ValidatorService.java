package com.mfdigital.apollo_users.core.http.services;

import com.mfdigital.apollo_users.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidatorService {


    private AuthUserDetails authUserDetails;

    public static class AuthUserDetails{
        User authUserWhoRequestingChange = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        public String roleAuthUser(){
            return authUserWhoRequestingChange.getUserRole().toString();
        }

        public String sectorAuthUser() {
            return authUserWhoRequestingChange.getSector().toString();
        }

        public String stateAuthUser() {
            return authUserWhoRequestingChange.getState().toString();
        }
    }


    public void roleValidate(String roleUpdateUser){

        String roleAuthUser = authUserDetails.roleAuthUser();

        if(roleAuthUser.equals("COLLABORATOR")){
            throw new AccessDeniedException("Collaborators cannot update others users");
        }

        if (
                (roleUpdateUser.equals("ADMIN") || roleUpdateUser.equals("COORDINATOR"))
                        &&
                (roleAuthUser.equals("COORDINATOR") || roleAuthUser.equals("SUPERVISOR"))
        )
        {
            throw new AccessDeniedException("Coordinators or supervisor cannot update admin or coordinator users");
        }


        if(roleUpdateUser.equals("SUPERVISOR") && roleAuthUser.equals("SUPERVISOR")){
            throw new AccessDeniedException("Supervisors cannot update others supervisors");
        }
    }

    public void sectorValidate(String sectorUpdateUser){

        String roleAuthUser = authUserDetails.roleAuthUser();
        String sectorAuthUser = authUserDetails.sectorAuthUser();

        if(!Objects.equals(sectorAuthUser, sectorUpdateUser) && !Objects.equals(roleAuthUser, "ADMIN")){
            throw new AccessDeniedException("You can only update users with the same sector");
        }
    }

    public void stateValidate(String stateUpdateUser){

        String roleAuthUser = authUserDetails.roleAuthUser();
        String stateAuthUser = authUserDetails.stateAuthUser();

        if(!Objects.equals(stateAuthUser, stateUpdateUser) && ((!Objects.equals(roleAuthUser, "ADMIN") || !Objects.equals(roleAuthUser, "COORDINATOR") ))){
            throw new AccessDeniedException("You can only update users with the same sector");
        }
    }

}
