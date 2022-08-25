package com.epam.rd.project.payments.services;

import com.epam.rd.project.payments.model.dtos.UserDTO;
import com.epam.rd.project.payments.model.enums.RoleType;

import java.util.Optional;

public interface UserService {

    UserDTO createUser(UserDTO user);
    void assignRoleToUser(String userName, RoleType roleType);
    Optional<UserDTO> findByUserName(String userName);

}
