package com.epam.rd.project.payments.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreationBodyDTO {

    private String username;
    private String initialBalance;
    private String currency;
}
