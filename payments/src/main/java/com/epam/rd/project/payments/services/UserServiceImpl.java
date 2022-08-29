package com.epam.rd.project.payments.services;

import com.epam.rd.project.payments.model.dtos.UserDTO;
import com.epam.rd.project.payments.model.enums.RoleType;
import com.epam.rd.project.payments.model.mappers.UserMapper;
import com.epam.rd.project.payments.repositories.RoleRepository;
import com.epam.rd.project.payments.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final String USER_CREATED_MESSAGE = "User %s created with id %d";
    private static final String ROLE_ASSIGNED_MESSAGE = "Role %s assigned to user %s";
    private static final String USER_NOT_FOUND_MESSAGE = "User %s not found";
    private static final String USER_FOUND_MESSAGE = "User {} found in the database";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()){
            var errorMessage = String.format(USER_NOT_FOUND_MESSAGE, username);
            log.error(errorMessage);
         throw new UsernameNotFoundException(errorMessage);
        }
        var user = userOptional.get();
        log.info(USER_FOUND_MESSAGE, username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName().name())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    @Transactional
    public UserDTO createUser(UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var userCreated = userRepository.save(UserMapper.INSTANCE.mapToEntity(user));
        log.info(String.format(USER_CREATED_MESSAGE, user.getUsername(), userCreated.getId()));
        return UserMapper.INSTANCE.mapFromEntity(userCreated);
    }

    @Override
    @Transactional
    public void assignRoleToUser(String userName, RoleType roleType) {
        var userOptional= userRepository.findByUsername(userName);
        userOptional.ifPresent(user->{
          var roleOptional=  roleRepository.findByName(roleType);
          roleOptional.ifPresent(role -> user.getRoles().add(role));
          log.info(String.format(ROLE_ASSIGNED_MESSAGE, roleType.name(), user.getUsername()));
        });
    }

    @Override
    public Optional<UserDTO> findByUserName(String userName) {
        var userOptional = userRepository.findByUsername(userName);
        return userOptional.map(UserMapper.INSTANCE::mapFromEntity);
    }
}
