package com.mfdigital.apollo_users.core.http.service;


import com.mfdigital.apollo_users.core.entity.Collaborator;
import com.mfdigital.apollo_users.core.entity.Supervisor;
import com.mfdigital.apollo_users.core.repositories.SupervisorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupervisorService{

    private final SupervisorRepository supervisorRepository;

    @Autowired
    public SupervisorService(SupervisorRepository supervisorRepository){
        this.supervisorRepository = supervisorRepository;
    }

    public List<Supervisor> getAllSupervisors(){
        return supervisorRepository.findAll();
    }

    public Optional<Supervisor>getSupervisorById(String id){
        UUID idSupervisor = UUID.fromString(id);
        return supervisorRepository.findById(idSupervisor);
    }

    public Supervisor createSupervisor(Supervisor supervisor){
        return supervisorRepository.save(supervisor);
    }

    public Supervisor updateSupervisor(String id, Supervisor updatedSupervisor){
        UUID idSupervisor = UUID.fromString(id);
        Supervisor existantSupervisor = supervisorRepository.findById(idSupervisor)
                .orElseThrow(()-> new EntityNotFoundException("Supervisor not found."));
        updatedSupervisor.setId(existantSupervisor.getId());
        return supervisorRepository.save(updatedSupervisor);
    }

    public Supervisor inactivateSupervisor(String id){
        UUID idSupervisor = UUID.fromString(id);
        Supervisor supervisor = supervisorRepository.findById(idSupervisor)
                .orElseThrow(()-> new EntityNotFoundException("Supervisor not found."));
        supervisor.setId(idSupervisor);
        supervisor.setActive(false);
        return supervisorRepository.save(supervisor);
    }

}