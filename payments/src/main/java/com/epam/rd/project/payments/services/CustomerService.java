package com.epam.rd.project.payments.services;

import com.epam.rd.project.payments.model.dtos.CustomerDTO;

public interface CustomerService {

    CustomerDTO registerCustomer(CustomerDTO customer);
}
