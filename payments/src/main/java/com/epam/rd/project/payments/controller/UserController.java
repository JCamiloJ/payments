package com.epam.rd.project.payments.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.epam.rd.project.payments.model.dtos.RoleRequestDTO;
import com.epam.rd.project.payments.model.dtos.UserDTO;
import com.epam.rd.project.payments.model.enums.RoleType;
import com.epam.rd.project.payments.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private static final String ERROR_ROLE_TO_USER = "Role {} doesn't exists";
    private static final String LOGGING_ERROR_MESSAGE = "Error logging in: {}";
    private static final String MASK = "*****";

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/create").toUriString());
        var userCreated = userService.createUser(user);
        userCreated.setPassword(MASK);
        return (ResponseEntity.created(uri).body(userCreated));
    }

    @PostMapping("/addRole")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleRequestDTO roleRequest){
        if(Arrays.stream(RoleType.values()).noneMatch(roleType -> roleType.name().equals(roleRequest.getRoleName()))){
            log.error(ERROR_ROLE_TO_USER, roleRequest.getRoleName());
            return (ResponseEntity.badRequest().body("Invalid Role"));
        }
        userService.assignRoleToUser(roleRequest.getUserName(), RoleType.valueOf(roleRequest.getRoleName()));
        return (ResponseEntity.ok().build());
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decoded = verifier.verify(refreshToken);
                String username = decoded.getSubject();
                Optional<UserDTO> userOptional = userService.findByUserName(username);
                if(userOptional.isEmpty()){
                    throw new ChangeSetPersister.NotFoundException();
                }
                var user = userOptional.get();
                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(Instant.now().plusSeconds(600))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(roleDTO -> roleDTO
                                .getName().name())
                                .collect(Collectors.joining()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessToken", accessToken);
                tokens.put("refreshToken", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception){
                log.error(LOGGING_ERROR_MESSAGE, exception.getMessage());
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
            throw new RuntimeException("Refresh token is missing");
        }
    }

}
