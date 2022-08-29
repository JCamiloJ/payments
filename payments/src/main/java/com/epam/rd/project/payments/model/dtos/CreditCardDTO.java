package com.epam.rd.project.payments.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDTO {

    private Long id;
    private String cardNumber;
    private String ccv;
    private String nameInCard;
    private LocalDate expirationDate;
    private String accountNumber;
}
