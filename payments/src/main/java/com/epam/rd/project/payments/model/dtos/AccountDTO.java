package com.epam.rd.project.payments.model.dtos;

import com.epam.rd.project.payments.model.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private long accountId;
    private String accountNumber;
    private BigDecimal balance;
    private CurrencyType currency;
    private CustomerDTO customer;
    private CreditCardDTO creditCard;
    private boolean blocked;

}
