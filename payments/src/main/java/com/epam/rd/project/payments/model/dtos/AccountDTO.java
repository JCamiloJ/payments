package com.epam.rd.project.payments.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

    private long accountId;
    private String accountName;
    private Double Balance;

}
