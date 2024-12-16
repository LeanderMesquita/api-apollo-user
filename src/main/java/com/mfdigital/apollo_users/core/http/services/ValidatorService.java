package com.mfdigital.apollo_users.core.http.services;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidatorService {


    public void roleValidate(String roleUpdateUser, String roleAuthUser){

        if(roleAuthUser.equals("COLLABORATOR")){
            throw new AccessDeniedException("Acesso negado.");
        }

        if (
                (roleUpdateUser.equals("ADMIN") || roleUpdateUser.equals("COORDINATOR"))
                        &&
                (roleAuthUser.equals("COORDINATOR") || roleAuthUser.equals("SUPERVISOR"))
        )
        {
            throw new AccessDeniedException("Coordenadores ou supervisores não possuem autorização para isso.");
        }


        if(roleUpdateUser.equals("SUPERVISOR") && roleAuthUser.equals("SUPERVISOR")){
            throw new AccessDeniedException("Supervisores não possuem autorização para isso.");
        }
    }

    public void sectorValidate(String sectorUpdateUser, String sectorAuthUser, String roleAuthUser){

        if(!Objects.equals(sectorAuthUser, sectorUpdateUser) && Objects.equals(roleAuthUser, "ADMIN")){
            throw new AccessDeniedException("Você só pode alterar um usuário do mesmo setor.");
        }
    }

    public void stateValidate(String stateUpdateUser, String stateAuthUser, String roleAuthUser){

        if(!Objects.equals(stateAuthUser, stateUpdateUser) && ((!Objects.equals(roleAuthUser, "ADMIN") || !Objects.equals(roleAuthUser, "COORDINATOR") ))){
            throw new AccessDeniedException("Você só pode alterar um usuário do mesmo estado.");
        }
    }

}
