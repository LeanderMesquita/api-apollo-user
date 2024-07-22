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

    private final CollaboratorRepository collaboratorRepository;

    @Autowired
    public CollaboratorService(CollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }

    public List<Collaborator>getAllCollborators(){
        return collaboratorRepository.findAll();
    }

    public Optional<Collaborator>getCollaboratorById(String id){
        UUID idCollaborator = UUID.fromString(id);
        return collaboratorRepository.findById(idCollaborator);
    }

    public Collaborator createCollaborator(Collaborator collaborator){
        return collaboratorRepository.save(collaborator);
    }

    public Collaborator updateCollaborator(String id, Collaborator collaboratorDetails){
        UUID idCollaborator = UUID.fromString(id);
        Collaborator collaborator = collaboratorRepository.findById(idCollaborator).orElseThrow();

        collaborator.setName(collaboratorDetails.getName());
        collaborator.setEmail(collaboratorDetails.getEmail());
        collaborator.setActive(collaboratorDetails.getActive());

        return  collaboratorRepository.save(collaborator);
    }

    public Collaborator inactivateCollaborator (String id) {
        UUID idCollaborator = UUID.fromString(id);
        Collaborator collaborator = collaboratorRepository.findById(idCollaborator).orElseThrow(() -> new EntityNotFoundException("Collaborator not found"));
        collaborator.setId(idCollaborator);
        collaborator.setActive(false);
        return collaboratorRepository.save(collaborator);
    }
}
