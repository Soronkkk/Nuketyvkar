package com.project.pet.nuketyvkar.repositories;

import com.project.pet.nuketyvkar.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
