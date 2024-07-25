package com.mfdigital.apollo_users.core.http.controller;

import com.mfdigital.apollo_users.core.entity.Collaborator;
import com.mfdigital.apollo_users.core.http.service.CollaboratorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/collaborator")
public class CollaboratorController {
    private final CollaboratorService collaboratorService;

    @Autowired
    public CollaboratorController(CollaboratorService collaboratorService) {
        this.collaboratorService = collaboratorService;
    }

    @GetMapping("/view")
    public ResponseEntity<List<Collaborator>> getAllCollaborators() {
        List<Collaborator> collaborators = collaboratorService.getAllCollborators();
        return new ResponseEntity<>(collaborators, HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Collaborator> getCollaboratorById(@PathVariable String id){
        Optional<Collaborator> collaborator = collaboratorService.getCollaboratorById(id);
        return collaborator.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Collaborator> createCollaborator(@RequestBody Collaborator collaborator) {
        Collaborator newCollaborator = collaboratorService.createCollaborator(collaborator);
        return new ResponseEntity<>(newCollaborator, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Collaborator> updateCollaborator(@PathVariable String id, @RequestBody Collaborator collaborator){
        Optional<Collaborator> existingCollaborator = collaboratorService.getCollaboratorById(id);
        if (existingCollaborator.isPresent()) {
            Collaborator updateCollaborator = existingCollaborator.get();
            BeanUtils.copyProperties(collaborator, updateCollaborator, "id", "status");

            Collaborator updatedCollaborator = collaboratorService.updateCollaborator(id, updateCollaborator);
            return ResponseEntity.ok(updatedCollaborator);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Collaborator> inactivateCollaborator(@PathVariable String id){
        Collaborator inactivateCollaborator = collaboratorService.inactivateCollaborator(id);
        return ResponseEntity.ok(inactivateCollaborator);
    }
}
