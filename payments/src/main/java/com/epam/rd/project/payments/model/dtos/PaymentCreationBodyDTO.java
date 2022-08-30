package com.epam.rd.project.payments.model.dtos;

import com.epam.rd.project.payments.model.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreationBodyDTO {

    private Customer customer;
    private String amount;
    private String currency;
}
