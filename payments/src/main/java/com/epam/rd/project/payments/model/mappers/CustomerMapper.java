package com.epam.rd.project.payments.model.mappers;

import com.epam.rd.project.payments.model.dtos.PaymentDTO;
import com.epam.rd.project.payments.model.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    PaymentDTO mapFromEntity(Customer custome);

}
