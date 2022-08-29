package com.epam.rd.project.payments.model.mappers;

import com.epam.rd.project.payments.model.dtos.AccountDTO;
import com.epam.rd.project.payments.model.dtos.PaymentDTO;
import com.epam.rd.project.payments.model.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDTO mapFromEntity(Account account);
}
