package com.mfdigital.apollo_users.core.http.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfdigital.apollo_users.core.entity.Collaborator;
import com.mfdigital.apollo_users.core.entity.Coordinator;
import com.mfdigital.apollo_users.core.entity.Supervisor;
import com.mfdigital.apollo_users.core.exceptions.CollaboratorNotFoundException;
import com.mfdigital.apollo_users.core.exceptions.SupervisorNotFoundException;
import com.mfdigital.apollo_users.core.repositories.CollaboratorRepository;
import com.mfdigital.apollo_users.core.repositories.CoordinatorRepository;
import com.mfdigital.apollo_users.core.repositories.SupervisorRepository;


@Service
public class AdminService {
    @Autowired
    private CoordinatorRepository coordinatorRepository;
    @Autowired
    private SupervisorRepository supervisorRepository;
    @Autowired
    private CollaboratorRepository collaboratorRepository;

    public List<Coordinator> getAllCoordinators() {
        return coordinatorRepository.findAll();
    }

    public List<Supervisor> getAllSupervisors() {
        return supervisorRepository.findAll();
    }

    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.findAll();
    }

    public Coordinator createCoordinator(Coordinator coordinator) {
        return coordinatorRepository.save(coordinator);
    }

    public Supervisor createSupervisor(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    public Collaborator createCollaborator(Collaborator collaborator) {
        return collaboratorRepository.save(collaborator);
    }

    public Coordinator findCoordinatorById(String id) {
        UUID idCoordinator = UUID.fromString(id);
        Optional<Coordinator> coordinatorOptional = coordinatorRepository.findById(idCoordinator);
        return coordinatorOptional.get();
    }

    public Supervisor findSupervisorById(String id) {
        UUID idSupervisor = UUID.fromString(id);
        Optional<Supervisor> supervisorOptional = supervisorRepository.findById(idSupervisor);
        return supervisorOptional.get();
    }

    public Collaborator findCollaboratorById(String id) {
        UUID idCollaborator = UUID.fromString(id);
        Optional<Collaborator> collaboratorOptional = collaboratorRepository.findById(idCollaborator);
        return collaboratorOptional.get();
    }

    public Coordinator inactivateCoordinator(String id) {
        Coordinator existingCoordinator = findCoordinatorById(id);
        existingCoordinator.setActive(false);
        return coordinatorRepository.save(existingCoordinator);
    }

    public Supervisor inactivateSupervisor(String id) {
        Supervisor existingSupervisor = findSupervisorById(id);
        existingSupervisor.setActive(false);
        return supervisorRepository.save(existingSupervisor);
    }

    public Collaborator inactivateCollaborator(String id) {
        Collaborator existingCollaborator = findCollaboratorById(id);
        existingCollaborator.setActive(false);
        return collaboratorRepository.save(existingCollaborator);
    }

    public Coordinator activateCoordinator(String id) {
        Coordinator existingCoordinator = findCoordinatorById(id);
        existingCoordinator.setActive(true);
        return coordinatorRepository.save(existingCoordinator);
    }

    public Supervisor activateSupervisor(String id) {
        Supervisor existingSupervisor = findSupervisorById(id);
        existingSupervisor.setActive(true);
        return supervisorRepository.save(existingSupervisor);
    }

    public Collaborator activateCollaborator(String id) {
        Collaborator existingCollaborator = findCollaboratorById(id);
        existingCollaborator.setActive(true);
        return collaboratorRepository.save(existingCollaborator);
    }
}
