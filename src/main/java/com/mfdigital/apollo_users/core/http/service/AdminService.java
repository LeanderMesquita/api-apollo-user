package com.mfdigital.apollo_users.core.http.service;

import com.mfdigital.apollo_users.core.entity.Admin;
import com.mfdigital.apollo_users.core.entity.Coordinator;
import com.mfdigital.apollo_users.core.entity.Supervisor;
import com.mfdigital.apollo_users.core.entity.Collaborator;
import com.mfdigital.apollo_users.core.repositories.AdminRepository;
import com.mfdigital.apollo_users.core.repositories.CoordinatorRepository;
import com.mfdigital.apollo_users.core.repositories.SupervisorRepository;
import com.mfdigital.apollo_users.core.repositories.CollaboratorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(String id){
        UUID idAdmin = UUID.fromString(id);
        return adminRepository.findById(idAdmin);
    }

    public Admin create(Admin admin){
        return adminRepository.save(admin);
    }

    public Admin update(String id, Admin updatedAdmin){
        UUID idAdmin = UUID.fromString(id);
        Admin existantAdmin = adminRepository.findById(idAdmin)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        updatedAdmin.setId(existantAdmin.getId());
        return adminRepository.save(updatedAdmin);
    }

    public Admin inactivate(String id, Admin updatedAdmin){
        UUID idAdmin = UUID.fromString(id);
        Admin existantAdmin = adminRepository.findById(idAdmin)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        updatedAdmin.setId(existantAdmin.getId());
        updatedAdmin.setActive(false);
        return adminRepository.save(updatedAdmin);
    }
}
