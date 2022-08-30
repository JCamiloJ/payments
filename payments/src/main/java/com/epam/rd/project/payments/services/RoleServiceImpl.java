package com.epam.rd.project.payments.services;

import com.epam.rd.project.payments.model.dtos.RoleDTO;
import com.epam.rd.project.payments.model.entities.Role;
import com.epam.rd.project.payments.model.enums.RoleType;
import com.epam.rd.project.payments.model.mappers.RoleMapper;
import com.epam.rd.project.payments.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Class that implements the role service interface
 *
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private static final String ROLE_CREATED_MESSAGE = "Role %s created";

    private final RoleRepository roleRepository;


    @Override
    public RoleDTO createRole(RoleDTO role) {
        Role roleCreated = roleRepository.save(RoleMapper.INSTANCE.mapToEntity(role));
        log.info(String.format(ROLE_CREATED_MESSAGE, role.getName()));
        return RoleMapper.INSTANCE.mapToDTO(roleCreated);
    }

    /**
     * Find a role by the name
     *
     * @return an optional of RoleDTO with the role
     */
    @Override
    public Optional<RoleDTO> findByName(RoleType name) {
        var roleOptional = roleRepository.findByName(name);
        return roleOptional.map(RoleMapper.INSTANCE::mapToDTO);
    }
}
