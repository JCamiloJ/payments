package com.epam.rd.project.payments.model.mappers;

import com.epam.rd.project.payments.model.dtos.CreditCardDTO;
import com.epam.rd.project.payments.model.entities.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditCardMapper {

    CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);

    CreditCardDTO mapFromEntity(CreditCard creditCard);
}
