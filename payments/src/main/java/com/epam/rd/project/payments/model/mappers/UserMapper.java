package com.epam.rd.project.payments.model.mappers;

import com.epam.rd.project.payments.model.dtos.RoleDTO;
import com.epam.rd.project.payments.model.dtos.UserDTO;
import com.epam.rd.project.payments.model.entities.Role;
import com.epam.rd.project.payments.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO mapFromEntity(User user);
    List<RoleDTO> mapToRoleListDTO(List<Role> roles);
    User mapToEntity(UserDTO user);
    List<Role> mapRoleList(List<RoleDTO> roles);
}
