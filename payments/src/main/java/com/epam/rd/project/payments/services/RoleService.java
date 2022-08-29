package com.epam.rd.project.payments.services;

import com.epam.rd.project.payments.model.dtos.RoleDTO;
import com.epam.rd.project.payments.model.enums.RoleType;

import java.util.Optional;

public interface RoleService {

    RoleDTO createRole(RoleDTO role);
    Optional<RoleDTO> findByName(RoleType name);
}
