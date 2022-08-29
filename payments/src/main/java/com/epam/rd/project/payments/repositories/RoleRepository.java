package com.epam.rd.project.payments.repositories;

import com.epam.rd.project.payments.model.entities.Role;
import com.epam.rd.project.payments.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}
