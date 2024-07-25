package com.mfdigital.apollo_users.core.http.controller;

import com.mfdigital.apollo_users.core.entity.Supervisor;
import com.mfdigital.apollo_users.core.http.service.SupervisorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/supervisor")
public class SupervisorController {
    private final SupervisorService supervisorService;

    @Autowired
    public SupervisorController(SupervisorService supervisorService){
        this.supervisorService = supervisorService;
    }

    @GetMapping("/view")
    public ResponseEntity<List<Supervisor>> getAllSupervisors() {
        List<Supervisor> supervisors = supervisorService.getAllSupervisors();
        return new ResponseEntity<>(supervisors, HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Supervisor> getSupervisorById(String id) {
        Optional<Supervisor> supervisor = supervisorService.getSupervisorById(id);
        return supervisor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Supervisor> createSupervisor(@RequestBody Supervisor supervisor) {
        Supervisor newSupervisor = supervisorService.createSupervisor(supervisor);
        return new ResponseEntity<>(newSupervisor, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Supervisor> updateSupervisor(@PathVariable String id, @RequestBody Supervisor supervisor){
        Optional<Supervisor> existingSupervisor = supervisorService.getSupervisorById(id);
        if(existingSupervisor.isPresent()) {
            Supervisor updateSupervisor = existingSupervisor.get();
            BeanUtils.copyProperties(supervisor, updateSupervisor, "id", "status");

            Supervisor updatedSupervisor = supervisorService.updateSupervisor(id, updateSupervisor);
            return ResponseEntity.ok(updatedSupervisor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Supervisor> inactivateSupervisor(@PathVariable String id) {
        Supervisor inactivateSupervisor = supervisorService.inactivateSupervisor(id);
        return ResponseEntity.ok(inactivateSupervisor);
    }

}