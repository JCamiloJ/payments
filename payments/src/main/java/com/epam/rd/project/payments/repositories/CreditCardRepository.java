package com.epam.rd.project.payments.repositories;

import com.epam.rd.project.payments.model.entities.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
