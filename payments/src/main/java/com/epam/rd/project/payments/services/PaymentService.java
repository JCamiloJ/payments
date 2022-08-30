package com.epam.rd.project.payments.services;

import com.epam.rd.project.payments.model.dtos.PaymentCreationBodyDTO;
import com.epam.rd.project.payments.model.dtos.PaymentDTO;

public interface PaymentService {

    PaymentDTO preparePayment(PaymentCreationBodyDTO accountRequest);
    PaymentDTO makePayment(PaymentCreationBodyDTO accountRequest);
}
