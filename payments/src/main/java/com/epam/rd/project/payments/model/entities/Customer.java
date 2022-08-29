package com.epam.rd.project.payments.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Customer extends User {

    @OneToMany(mappedBy="customer")
    private Set<Account> accounts;

    @OneToMany(mappedBy="customer")
    private Set<Payment> payments;
}
