package com.epam.rd.project.payments.services;


import com.epam.rd.project.payments.model.dtos.AccountCreationBodyDTO;
import com.epam.rd.project.payments.model.dtos.AccountDTO;

public interface AccountService {
    AccountDTO createAccount(AccountCreationBodyDTO accountRequest);

}
