package com.epam.rd.project.payments.controller;

import com.epam.rd.project.payments.model.dtos.PaymentCreationBodyDTO;
import com.epam.rd.project.payments.model.dtos.PaymentDTO;
import com.epam.rd.project.payments.services.PaymentService;
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
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/makePayment")
    public ResponseEntity<PaymentDTO> pay(@RequestBody PaymentCreationBodyDTO payment){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/payment/makePayment").toUriString());
        return (ResponseEntity.created(uri).body(paymentService.makePayment(payment)));
    }

}
