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
    public ResponseEntity<Coordinator> findCoordenatorById(@PathVariable String id){
        Coordinator getCoordenatorById = adminService.findCoordinatorById(id);
        return ResponseEntity.ok(getCoordenatorById);
    }

    @GetMapping("/")
    public ResponseEntity<Supervisor> getSupervisorsById(@PathVariable String id){
        Supervisor getSupervisorsById = adminService.findSupervisorById(id);
        return ResponseEntity.ok(getSupervisorsById);
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
    public ResponseEntity<Admin> updateAdmin(@PathVariable String id, @RequestBody Admin admin {
        Optional<Admin> existingAdmin = adminService.getAdminById(id);
        if (existingAdmin.isPresent()){
            Admin updateAdmin = existingAdmin.get();
            BeanUtils.copyProperties(admin,updateAdmin,"id");

            Admin updateAdmin = adminService.updateAdmin(id,updateAdmin);
            return ResponseEntity.ok(updateAdmin);

        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
