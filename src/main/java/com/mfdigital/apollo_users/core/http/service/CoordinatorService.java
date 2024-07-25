package com.mfdigital.apollo_users.core.http.service;

import com.mfdigital.apollo_users.core.entity.Coordinator;
import com.mfdigital.apollo_users.core.repositories.CoordinatorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CoordinatorService {
    private final CoordinatorRepository coordinatorRepository;

    @Autowired
    public CoordinatorService(CoordinatorRepository coordinatorRepository) {
        this.coordinatorRepository = coordinatorRepository;
    }

    public List<Coordinator> getAllCoordinators() {
        return coordinatorRepository.findAll();
    }

    public Optional<Coordinator> getCoordinatorById(String id) {
        UUID idCoordinator = UUID.fromString(id);
        return coordinatorRepository.findById(idCoordinator);
    }

    public Coordinator createCoordinator(Coordinator coordinator) {
        return coordinatorRepository.save(coordinator);
    }

    public Coordinator updateCoordinator(String id, Coordinator updateCoordinator) {
        UUID idCoordinator = UUID.fromString(id);
        Coordinator existingCoordinator = coordinatorRepository.findById(idCoordinator)
                .orElseThrow(() -> new EntityNotFoundException("Coordinator not found."));
        updateCoordinator.setId(existingCoordinator.getId());
        return coordinatorRepository.save(updateCoordinator);
    }

    public Coordinator inactivateCoordinator(String id, Coordinator updateCoordinator) {
        UUID idCoordinator = UUID.fromString(id);
        Coordinator existingCoordinator = coordinatorRepository.findById(idCoordinator)
                .orElseThrow(() -> new EntityNotFoundException("Coordinator not found"));
        updateCoordinator.setId(existingCoordinator.getId());
        updateCoordinator.setActive(false);
        return coordinatorRepository.save(updateCoordinator);
    }

    public void deleteCoordinator(String id) {
        UUID idCoordinator = UUID.fromString(id);
        coordinatorRepository.deleteById(idCoordinator);
    }
}