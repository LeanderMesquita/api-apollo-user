package com.mfdigital.apollo_users.core.http.controller;

import com.mfdigital.apollo_users.core.entity.Supervisor;
import com.mfdigital.apollo_users.core.http.service.SupervisorService;
import com.mfdigital.apollo_users.core.repositories.SupervisorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.Beans;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class SupervisorController {
    private final SupervisorService supervisorService;

    @Autowired
    public SupervisorController(SupervisorService supervisorService){
        this.supervisorService = supervisorService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Supervisor>> getAllSupervisors() {
        List<Supervisor> supervisors = supervisorService.getAllSupervisors();
        return new ResponseEntity<>(supervisors, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Supervisor> getSupervisorById(String id) {
        UUID idSupervisor = UUID.fromString(id);
        Optional<Supervisor> supervisor = supervisorService.getSupervisorById(id);
        return supervisor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Supervisor> createSupervisor(@RequestBody Supervisor supervisor) {
        Supervisor newSupervisor = supervisorService.createSupervisor(supervisor);
        return new ResponseEntity<>(newSupervisor, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Supervisor> updateSupervisor(@PathVariable String id, @RequestBody Supervisor supervisor){
        Optional<Supervisor> existingSupervisor = supervisorService.getSupervisorById(id);
        if(existingSupervisor.isPresent()) {
            Supervisor updateSupervisor = existingSupervisor.get();
            BeanUtils.copyProperties(supervisor, updateSupervisor, id);

            Supervisor updatedSuepervisor = supervisorService.updateSupervisor(id, updateSupervisor);
            return ResponseEntity.ok(updatedSuepervisor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<Supervisor> inactivateSupervisor(String id) {
        Supervisor inactivateSupervisor = supervisorService.inactivateSupervisor(id);
        return ResponseEntity.ok(inactivateSupervisor);
    }


}