package com.epam.rd.project.payments.services;

import com.epam.rd.project.payments.exceptions.UserNotFoundException;
import com.epam.rd.project.payments.model.dtos.PaymentCreationBodyDTO;
import com.epam.rd.project.payments.model.dtos.PaymentDTO;
import com.epam.rd.project.payments.model.entities.Payment;
import com.epam.rd.project.payments.model.enums.CurrencyType;
import com.epam.rd.project.payments.model.enums.PaymentStatus;
import com.epam.rd.project.payments.model.mappers.PaymentMapper;
import com.epam.rd.project.payments.repositories.AccountRepository;
import com.epam.rd.project.payments.repositories.CustomerRepository;
import com.epam.rd.project.payments.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * Class that implements the payment service interface
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private static final String NOT_FOUND_EXCEPTION = "User %s not found in database";

    private PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Override
    public PaymentDTO preparePayment(PaymentCreationBodyDTO paymenRequest) {
        var paymentOptional = customerRepository.findByUsername(paymenRequest.getCustomer().getUsername());
        if(paymentOptional.isPresent()){
            var payment = Payment
                    .builder()
                    .amount(new BigDecimal(paymenRequest.getAmount()))
                    .creationDate(LocalDate.now())
                    .currency(CurrencyType.valueOf(paymenRequest.getCurrency()))
                    .customer(paymenRequest.getCustomer())
                    .status(PaymentStatus.PREPARED)
                    .build();
            var savedPayment = paymentRepository.save(payment);
            return PaymentMapper.INSTANCE.mapFromEntity(savedPayment);
        }else{
            var errorMessage = String.format(NOT_FOUND_EXCEPTION, paymenRequest.getCustomer().getUsername());
            log.error(errorMessage);
            throw new UserNotFoundException(errorMessage);
        }
    }

   @Override
   public PaymentDTO makePayment(PaymentCreationBodyDTO paymentRequest) {
    //    var paymentOptional = customerRepository.findByUsername(paymentRequest.getCustomer().getUsername());
   //     if(paymentOptional.isPresent()){
    //        if(isPrepared(paymentRequest)){
    //            var customer = customerRepository.findByUsername(paymentRequest.getCustomer().getUsername());
     //       }
    //    }
        return null;
   }

    /**
     * gives true or false depends on the payment status
     *
     * @return true if the payment status is prepared or false if not
     */
    private boolean isPrepared(PaymentCreationBodyDTO paymentRequest){
        return preparePayment(paymentRequest).getStatus() == PaymentStatus.PREPARED;
    }
}
