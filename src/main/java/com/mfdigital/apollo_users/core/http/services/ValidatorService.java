package com.mfdigital.apollo_users.core.http.services;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidatorService {


    public void roleValidate(String roleUpdateUser, String roleAuthUser){

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

    public void sectorValidate(String sectorUpdateUser, String sectorAuthUser, String roleAuthUser){

        if(!Objects.equals(sectorAuthUser, sectorUpdateUser) && Objects.equals(roleAuthUser, "ADMIN")){
            throw new AccessDeniedException("You can only update users with the same sector");
        }
    }

    public void stateValidate(String stateUpdateUser, String stateAuthUser, String roleAuthUser){

        if(!Objects.equals(stateAuthUser, stateUpdateUser) && ((!Objects.equals(roleAuthUser, "ADMIN") || !Objects.equals(roleAuthUser, "COORDINATOR") ))){
            throw new AccessDeniedException("You can only update users with the same state");
        }
    }

}
