package com.mfdigital.apollo_users.core.http.service;

import com.mfdigital.apollo_users.core.entity.Collaborator;
import com.mfdigital.apollo_users.core.entity.Coordinator;
import com.mfdigital.apollo_users.core.repositories.CollaboratorRepository;
import jakarta.persistence.EntityNotFoundException;
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
        collaborator.setActive(collaboratorDetails.getActive());

        return  collaboratorRepository.save(collaborator);
    }

    public Collaborator isActive(UUID id) {
        Collaborator collaborator = collaboratorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Collaborator not found"));
        collaborator.setActive(false);
        return collaboratorRepository.save(collaborator);
    }
}
