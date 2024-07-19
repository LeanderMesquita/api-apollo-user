package com.mfdigital.apollo_users.core.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfdigital.apollo_users.core.entity.Collaborator;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, UUID> {

}
