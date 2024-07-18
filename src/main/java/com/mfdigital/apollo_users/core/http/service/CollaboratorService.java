package com.mfdigital.apollo_users.core.http.service;

import com.mfdigital.apollo_users.core.entity.Collaborator;
import com.mfdigital.apollo_users.core.repositories.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CollaboratorService {

    private CollaboratorRepository collaboratorRepository;

    @Autowired
    public CollaboratorService(CollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }

    public List<Collaborator>getAllCollborators(){
        return collaboratorRepository.findAll();
    }

    public Optional<Collaborator>getCollaboratorById(UUID idCollaborator){
        return collaboratorRepository.findById(idCollaborator);
    }

    public Collaborator createCollaborator(Collaborator collaborator){
        return collaboratorRepository.save(collaborator);
    }

    public Collaborator updateCollaborator(UUID idCollaborator, Collaborator collaboratorDetails){
        Collaborator collaborator = collaboratorRepository.findById(idCollaborator).orElseThrow();

        collaborator.setName(collaboratorDetails.getName());
        collaborator.setEmail(collaboratorDetails.getEmail());

        return  collaboratorRepository.save(collaborator);
    }
}
