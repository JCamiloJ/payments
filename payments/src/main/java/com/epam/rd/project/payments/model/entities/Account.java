package com.epam.rd.project.payments.model.entities;

import com.epam.rd.project.payments.model.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column
    private String accountNumber;

    @Column
    private BigDecimal balance;

    @Column
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    @Column
    private boolean blocked;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_card_id", referencedColumnName = "id")
    private CreditCard creditCard;
}
