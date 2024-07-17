package com.mfdigital.apollo_users.core.repositories;

import com.mfdigital.apollo_users.core.entity.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, UUID> {
}