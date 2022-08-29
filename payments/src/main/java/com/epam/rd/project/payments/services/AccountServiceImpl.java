package com.epam.rd.project.payments.services;

import com.epam.rd.project.payments.exceptions.UserNotFoundException;
import com.epam.rd.project.payments.model.dtos.AccountCreationBodyDTO;
import com.epam.rd.project.payments.model.dtos.AccountDTO;
import com.epam.rd.project.payments.model.entities.Account;
import com.epam.rd.project.payments.model.enums.CurrencyType;
import com.epam.rd.project.payments.model.mappers.AccountMapper;
import com.epam.rd.project.payments.repositories.AccountRepository;
import com.epam.rd.project.payments.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private static final String NOT_FOUND_EXCEPTION = "User : %s not found in database";

    @Override
    public AccountDTO createAccount(AccountCreationBodyDTO accountRequest) {
        var userOptional = customerRepository.findByUsername(accountRequest.getUsername());
        if(userOptional.isPresent()){
            var account = Account
                    .builder()
                    .accountNumber(generateAccountNumber())
                    .balance(new BigDecimal(accountRequest.getInitialBalance()))
                    .currency(CurrencyType.valueOf(accountRequest.getCurrency()))
                    .customer(userOptional.get())
                    .build();
            var savedAccount = accountRepository.save(account);
            return AccountMapper.INSTANCE.mapFromEntity(savedAccount);
        }else{
            var errorMessage = String.format(NOT_FOUND_EXCEPTION, accountRequest.getUsername());
            log.error(errorMessage);
            throw new UserNotFoundException(errorMessage);
        }
    }




    /**
     * Generate account number by parsing the instant milliseconds in which it was generated.
     *
     * @return the account number as a String
     */
    public String generateAccountNumber(){
        return "" + Instant.now().toEpochMilli();
    }
}
