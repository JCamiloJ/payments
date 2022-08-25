package com.epam.rd.project.payments.model.dtos;

import java.time.LocalDate;

public class CreditCardDTO {

    private Long id;
    private String cardNumber;
    private String ccv;
    private String nameInCard;
    private LocalDate expirationDate;
}
