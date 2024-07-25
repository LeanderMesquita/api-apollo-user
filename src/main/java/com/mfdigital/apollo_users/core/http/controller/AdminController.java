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
@RequestMapping("/users/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/view")
    public ResponseEntity<List<Admin>> getAllAdmin() {
        List<Admin> admin = adminService.getAllAdmin();
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Admin> getAdminById(String id) {
        Optional<Admin> admin = adminService.getAdminById(id);
        return admin.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin newAdmin = adminService.createAdmin(admin);
        return new ResponseEntity<>(newAdmin, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable String id, @RequestBody Admin admin){
        Optional<Admin> existingAdmin = adminService.getAdminById(id);
        if(existingAdmin.isPresent()) {
            Admin updateAdmin = existingAdmin.get();
            BeanUtils.copyProperties(admin, updateAdmin, "id", "status");

            Admin updateAdmin = adminService.updateAdmin(id, updateAdmin);
            return ResponseEntity.ok(updateAdmin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Admin> inactivateAdmin(@PathVariable String id) {
        Admin inactivateAdmin = adminService.inactivateAdmin(id);
        return ResponseEntity.ok(inactivateAdmin);
    }

}
