package com.epam.rd.project.payments.services;

import com.epam.rd.project.payments.model.dtos.UserDTO;
import com.epam.rd.project.payments.model.entities.User;
import com.epam.rd.project.payments.model.enums.RoleType;
import com.epam.rd.project.payments.model.mappers.UserMapper;
import com.epam.rd.project.payments.repositories.RoleRepository;
import com.epam.rd.project.payments.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String USER_CREATED_MESSAGE = "User %s created with id %d";
    private static final String ROLE_ASSIGNED_MESSAGE = "Role %s assigned to user %s";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO user) {
        var userCreated = userRepository.save(UserMapper.INSTANCE.mapToEntity(user));
        log.info(String.format(USER_CREATED_MESSAGE, user.getUserName(), userCreated.getId()));
        return UserMapper.INSTANCE.mapFromEntity(userCreated);
    }

    @Override
    @Transactional
    public void assignRoleToUser(String userName, RoleType roleType) {
        var userOptional= userRepository.findByUserName(userName);
        userOptional.ifPresent(user->{
          var roleOptional=  roleRepository.findByName(roleType.name());
          roleOptional.ifPresent(role -> user.getRoles().add(role));
          log.info(String.format(ROLE_ASSIGNED_MESSAGE, roleType.name(), user.getUserName()));
        });
    }

    @Override
    public Optional<UserDTO> findByUserName(String userName) {
        var userOptional = userRepository.findByUserName(userName);
        return userOptional.map(UserMapper.INSTANCE::mapFromEntity);
    }
}
