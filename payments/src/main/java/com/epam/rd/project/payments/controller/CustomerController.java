package com.epam.rd.project.payments.controller;

import com.epam.rd.project.payments.model.dtos.CustomerDTO;
import com.epam.rd.project.payments.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

    private static final  String MASK = "****";

    private final CustomerService customerService;


    @PostMapping("/register")
    public ResponseEntity<CustomerDTO> registerCustomer(@RequestBody CustomerDTO customer){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/customer/register").toUriString());
        var customerCreated = customerService.registerCustomer(customer);
        customerCreated.setPassword(MASK);
        return ResponseEntity.created(uri).body(customerCreated);
    }
}
