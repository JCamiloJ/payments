package com.epam.rd.project.payments.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Admin extends User {

    @Column
    private String phone;

}
