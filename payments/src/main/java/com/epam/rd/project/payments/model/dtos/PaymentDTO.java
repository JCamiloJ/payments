package com.epam.rd.project.payments.model.dtos;

import com.epam.rd.project.payments.model.entities.Customer;
import com.epam.rd.project.payments.model.enums.CurrencyType;
import com.epam.rd.project.payments.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long id;
    private LocalDate creationDate;
    private PaymentStatus status;
    private BigDecimal amount;
    private CurrencyType currency;
    private Customer customer;
}
