package com.epam.rd.project.payments.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cardNumber;
    @Column
    private String ccv;
    @Column
    private String nameInCard;

    @Column
    @DateTimeFormat(pattern="MM/yy")
    private LocalDate expirationDate;

    @OneToOne(mappedBy = "creditCard")
    private Account account;

}
