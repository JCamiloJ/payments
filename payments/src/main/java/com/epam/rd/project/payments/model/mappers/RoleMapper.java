package com.epam.rd.project.payments.model.mappers;

import com.epam.rd.project.payments.model.dtos.RoleDTO;
import com.epam.rd.project.payments.model.dtos.UserDTO;
import com.epam.rd.project.payments.model.entities.Role;
import com.epam.rd.project.payments.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO mapToDTO(Role role);

    Role mapToEntity(RoleDTO role);

}
