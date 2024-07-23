package com.mfdigital.apollo_users.core.http.controller;


import com.mfdigital.apollo_users.core.entity.Admin;
import com.mfdigital.apollo_users.core.entity.Collaborator;
import com.mfdigital.apollo_users.core.entity.Coordinator;
import com.mfdigital.apollo_users.core.entity.Supervisor;
import com.mfdigital.apollo_users.core.http.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService
    }

    @GetMapping("/")
    public ResponseEntity<List<Coordinator>> getAllACoordinators(){
        List<Coordinator> admin = adminService.getAllCoordinators();
        return  new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Collaborator>> getAllCollaborators(){
        List<Collaborator> admin = adminService.getAllCollaborators();
        return  new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Supervisor>> getAllSupervisors(){
        List<Supervisor> admin = adminService.getAllSupervisors();
        return  new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Collaborator> getCollaboratorById(@PathVariable String id){
        Collaborator getCollaboratorById = adminService.findCollaboratorById(id);
        return getCollaboratorById;
    }

    @GetMapping("/")
    public ResponseEntity<Supervisor> getSupervisorsById(@PathVariable String id){
        Optional<Supervisor> getAllCollaborators = adminService.findSupervisorById(id);
        return getAllCollaborators.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Coordinator> createCoordinator(@RequestBody Coordinator coordenator){
        Coordinator newCoordinator = adminService.createCoordinator(coordenator);
        return new ResponseEntity<>(newCoordinator, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Collaborator> createCollaborator(@RequestBody Collaborator collaborato){
        Collaborator newCollaborator = adminService.createCollaborator(collaborato);
        return new ResponseEntity<>(newCollaborator, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Supervisor> createSupervisor(@RequestBody Supervisor supervisor){
        Supervisor newSupervisor = adminService.createSupervisor(supervisor);
        return new ResponseEntity<>(newSupervisor, HttpStatus.OK);
    }



    @PutMapping("/")
    public ResponseEntity<Coordinator> updateCoordenator(@PathVariable String id, @RequestBody Coordinator admin {
        Optional<Coordinator> existingCoordenator = adminService.findCoordinatorById(id);
        if (existingCoordenator.isPresent()){
            Coordinator updateCoordenator = existingCoordenator.get();
            BeanUtils.copyProperties(admin,updateCoordenator,"id");

            Coordinator updateCoordenator = adminService.updateCoordenator(id,updateCoordenator);
            return ResponseEntity.ok(updateCoordenator);

        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
