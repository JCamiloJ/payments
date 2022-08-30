package com.epam.rd.project.payments.services;

import com.epam.rd.project.payments.exceptions.CustomerCreationException;
import com.epam.rd.project.payments.model.dtos.CustomerDTO;
import com.epam.rd.project.payments.model.entities.Customer;
import com.epam.rd.project.payments.model.enums.RoleType;
import com.epam.rd.project.payments.repositories.CustomerRepository;
import com.epam.rd.project.payments.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 *class that implements the Customer service interface
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private static final String CUSTOMER_CREATION_EXCEPTION = "Customer %s already exists";

    private final CustomerRepository customerRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public CustomerDTO registerCustomer(CustomerDTO customer) {
        if(userService.findByUserName(customer.getUsername()).isPresent()){
            var errorMessage = String.format(CUSTOMER_CREATION_EXCEPTION, customer.getUsername()) ;
            log.error(errorMessage);
            throw new CustomerCreationException(errorMessage);
        }else{
            var roleOptional = roleRepository.findByName(RoleType.CUSTOMER).orElse(null);
            var customerToRegister = Customer
                    .builder()
                    .username(customer.getUsername())
                    .password(passwordEncoder.encode(customer.getPassword()))
                    .accounts(Collections.emptySet())
                    .email(customer.getEmail())
                    .roles(List.of(roleOptional))
                    .build();
            var registeredCustomer = customerRepository.save(customerToRegister);
            return CustomerDTO
                    .builder()
                    .id(registeredCustomer.getId())
                    .username(registeredCustomer.getUsername())
                    .roles(registeredCustomer.getRoles())
                    .email(registeredCustomer.getEmail())
                    .build();
        }
    }
}
