package com.epam.rd.project.payments.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PaymentDTO {

    private Long id;
    private Date date;
}
