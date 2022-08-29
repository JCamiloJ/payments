package com.epam.rd.project.payments.model.entities;

import com.epam.rd.project.payments.model.enums.CurrencyType;
import com.epam.rd.project.payments.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate creationDate;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column
    private BigDecimal amount;

    @Column
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

}
