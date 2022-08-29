package com.epam.rd.project.payments.controller;

import com.epam.rd.project.payments.model.dtos.AccountCreationBodyDTO;
import com.epam.rd.project.payments.model.dtos.AccountDTO;
import com.epam.rd.project.payments.services.AccountService;
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
@RequestMapping("/account")
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountCreationBodyDTO accountRequest){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/account/create").toUriString());
        return (ResponseEntity.created(uri).body(accountService.createAccount(accountRequest)));
    }

}
