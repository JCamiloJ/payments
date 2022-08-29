package com.epam.rd.project.payments.services;

import com.epam.rd.project.payments.model.dtos.CreditCardCreationBodyDTO;
import com.epam.rd.project.payments.model.dtos.CreditCardDTO;

public interface CreditCardService {

    CreditCardDTO generateCreditCard(CreditCardCreationBodyDTO creditCard);
}
