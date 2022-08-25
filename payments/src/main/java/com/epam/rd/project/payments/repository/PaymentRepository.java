package com.epam.rd.project.payments.repository;

import com.epam.rd.project.payments.model.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAll();
    Payment findById(int id);
    List<Payment> findByDate(Date date);

}
