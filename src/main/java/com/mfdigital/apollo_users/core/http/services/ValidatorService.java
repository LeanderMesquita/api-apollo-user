package com.mfdigital.apollo_users.core.http.services;

import com.mfdigital.apollo_users.core.entity.enums.UserRole;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidatorService {


    public void roleValidate(String roleUpdateUser, String roleAuthUser){

        if(roleAuthUser.equals(UserRole.COLABORADOR.toString())){
            throw new AccessDeniedException("Acesso negado.");
        }

        if (
                (roleUpdateUser.equals(UserRole.ADMIN.toString()) || roleUpdateUser.equals(UserRole.COORDENADOR.toString()))
                        &&
                (roleAuthUser.equals(UserRole.COORDENADOR.toString()) || roleAuthUser.equals(UserRole.SUPERVISOR.toString()))
        )
        {
            throw new AccessDeniedException("Coordenadores ou supervisores não possuem autorização para isso.");
        }


        if(roleUpdateUser.equals(UserRole.SUPERVISOR.toString()) && roleAuthUser.equals(UserRole.SUPERVISOR.toString())){
            throw new AccessDeniedException("Supervisores não possuem autorização para isso.");
        }
    }

    public void sectorValidate(String sectorUpdateUser, String sectorAuthUser, String roleAuthUser){

        if(!Objects.equals(sectorAuthUser, sectorUpdateUser) && Objects.equals(roleAuthUser, UserRole.ADMIN.toString())){
            throw new AccessDeniedException("Você só pode alterar um usuário do mesmo setor.");
        }
    }

    public void stateValidate(String stateUpdateUser, String stateAuthUser, String roleAuthUser){

        if(!Objects.equals(stateAuthUser, stateUpdateUser) && ((!Objects.equals(roleAuthUser, UserRole.ADMIN.toString()) || !Objects.equals(roleAuthUser, UserRole.COORDENADOR.toString())))){
            throw new AccessDeniedException("Você só pode alterar um usuário do mesmo estado.");
        }
    }

}
