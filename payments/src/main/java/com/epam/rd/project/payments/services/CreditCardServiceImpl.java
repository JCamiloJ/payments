package com.epam.rd.project.payments.services;

import com.epam.rd.project.payments.exceptions.AccountNotFoundException;
import com.epam.rd.project.payments.exceptions.UserNotFoundException;
import com.epam.rd.project.payments.model.dtos.CreditCardCreationBodyDTO;
import com.epam.rd.project.payments.model.dtos.CreditCardDTO;
import com.epam.rd.project.payments.model.entities.Account;
import com.epam.rd.project.payments.model.entities.CreditCard;
import com.epam.rd.project.payments.model.entities.Customer;
import com.epam.rd.project.payments.model.mappers.CreditCardMapper;
import com.epam.rd.project.payments.repositories.AccountRepository;
import com.epam.rd.project.payments.repositories.CreditCardRepository;
import com.epam.rd.project.payments.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

/**
 * Class that implements the credit card service interface
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CreditCardServiceImpl implements CreditCardService{

    private static final String USER_ERROR_MESSAGE = "User %s not found";
    private static final String ACCOUNT_ERROR_MESSAGE = "Account %s not found";

    private final CreditCardRepository creditCardRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public CreditCardDTO generateCreditCard(CreditCardCreationBodyDTO creditCard) {
        var customer = validateCustomerExists(creditCard.getUsername());
        var account = validateAccountExists(creditCard.getAccountNumber());
        var creditCardGenerated = CreditCard
                .builder()
                .account(account)
                .cardNumber(generateCardNumber())
                .ccv(generateCCV())
                .expirationDate(LocalDate.now().plusYears(2).plusMonths(2))
                .nameInCard(creditCard.getNameInCard())
                .build();
        var creditCardSaved =creditCardRepository.save(creditCardGenerated);
        account.setCreditCard(creditCardSaved);
        var mapped= CreditCardMapper.INSTANCE.mapFromEntity(creditCardSaved);
        mapped.setAccountNumber(account.getAccountNumber());
        return mapped;
    }

    /**
     * Validate if a customer exists in the database
     *
     * @return a customer object
     */
    private Customer validateCustomerExists(String username){
        var customerOptional = customerRepository.findByUsername(username);
        if(customerOptional.isPresent()){
            return customerOptional.get();
        }else{
            var errorMessage = String.format(USER_ERROR_MESSAGE, username);
            log.error(errorMessage);
            throw new UserNotFoundException(errorMessage);
        }
    }

    /**
     * Validate if an account exists in the database
     *
     * @return an account object
     */
    private Account validateAccountExists(String accountNumber){
        var accountOptional = accountRepository.findByAccountNumber(accountNumber);
        if(accountOptional.isPresent()){
            return accountOptional.get();
        }else{
            var errorMessage = String.format(ACCOUNT_ERROR_MESSAGE, accountNumber);
            log.error(errorMessage);
            throw new AccountNotFoundException(errorMessage);
        }
    }

    /**
     * Generate a random number to assign it to a credit card
     *
     * @return a string with the generated card number
     */
    private String generateCardNumber(){
        StringBuilder generateNumber = new StringBuilder("");
        for(int i = 0; i < 4; i++){
            generateNumber.append((int)(Math.random()*(9999-1000+1)+1000));
        }
        return generateNumber.toString();
    }

    /**
     * Generate a random cvv number to assign it to a credit card
     *
     * @return a string with the generated cvv
     */
    private String generateCCV(){
        return "" + ((int)(Math.random()*(999-100+1)+100));
    }
}
