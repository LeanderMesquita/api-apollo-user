package com.mfdigital.apollo_users.core.http.service;

import com.mfdigital.apollo_users.core.entity.Coordinator;
import com.mfdigital.apollo_users.core.exceptions.CoordinatorNotFoundException;
import com.mfdigital.apollo_users.core.repositories.CoordinatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CoordinatorService {
    private final Coordinator coordinator;
    private final CoordinatorRepository coordinatorRepository;

    @Autowired
    public CoordinatorService(Coordinator coordinator, CoordinatorRepository coordinatorRepository) {
        this.coordinator = coordinator;
        this.coordinatorRepository = coordinatorRepository;
    }

    public List<Coordinator> getAllCoordinators() {
        return coordinatorRepository.findAll();
    }

    public Coordinator createCoordinator(Coordinator coordinator) {
        return coordinatorRepository.save(coordinator);
    }

    public Coordinator updateCoordinator(String id, Coordinator updateCoordinator) {
        UUID idCoordinator = UUID.fromString(id);
        Coordinator existingCoordinator = coordinatorRepository.findById(idCoordinator)
                .orElseThrow(() -> new CoordinatorNotFoundException("Coordinator not found."));
        updateCoordinator.setId(existingCoordinator.getId());
        return coordinatorRepository.save(updateCoordinator);
    }

    public Coordinator inactivateCoordinator(String id, Coordinator updateCoordinator) {
        UUID idCoordinator = UUID.fromString(id);
        Coordinator existingCoordinator = coordinatorRepository.findById(idCoordinator)
                .orElseThrow(() -> new CoordinatorNotFoundException("Coordinator not found"));
        updateCoordinator.setId(existingCoordinator.getId());
        updateCoordinator.setActive(false);
        return coordinatorRepository.save(updateCoordinator);
    }

    public void deleteCoordinator(String id) {
        UUID idCoordinator = UUID.fromString(id);
        coordinatorRepository.deleteById(idCoordinator);
    }
}