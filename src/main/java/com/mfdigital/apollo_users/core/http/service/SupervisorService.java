package com.mfdigital.apollo_users.core.http.service;


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

    public Supervisor createSupervisor(Supervisor supervisor){
        return supervisorRepository.save(supervisor);
    }

    public Supervisor updateSupervisor(UUID idSupervisor, Supervisor updatedSupervisor){
        Supervisor existantSupervisor = supervisorRepository.findById(idSupervisor)
                .orElseThrow(()-> new EntityNotFoundException("Supervisor not found."));

        return supervisorRepository.save(updatedSupervisor);
    }

    public void deleteSupervisor(UUID idSupervisor){
        supervisorRepository.deleteById(idSupervisor);
    }
}