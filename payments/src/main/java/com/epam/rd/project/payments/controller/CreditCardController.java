package com.epam.rd.project.payments.controller;

import com.epam.rd.project.payments.model.dtos.CreditCardCreationBodyDTO;
import com.epam.rd.project.payments.model.dtos.CreditCardDTO;
import com.epam.rd.project.payments.services.CreditCardService;
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
@RequestMapping("/creditCard")
@Slf4j
public class CreditCardController {

    private final CreditCardService creditCardService;

    @PostMapping("/addPaymentMethod")
    public ResponseEntity<CreditCardDTO> generateCreditCard(@RequestBody CreditCardCreationBodyDTO creditCard){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/creditCard/addPaymentMethod").toUriString());
     return (ResponseEntity.created(uri).body(creditCardService.generateCreditCard(creditCard)));
    }
}
