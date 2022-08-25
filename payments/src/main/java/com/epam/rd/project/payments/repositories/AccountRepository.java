package com.epam.rd.project.payments.repositories;

import com.epam.rd.project.payments.model.entities.Account;
import com.epam.rd.project.payments.model.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAll();
    Payment findById(int id);
    List<Payment> findByAccountName(String accountName);
    List<Payment> findByBalance(Double balance);
}
