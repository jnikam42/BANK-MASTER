package com.sarvatra.microservice.db.repository;

import com.sarvatra.microservice.db.entity.BankServiceMapDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankServiceMapRepository extends JpaRepository<BankServiceMapDB, Long> {
    boolean existsByBankIdAndServiceId(Long bankId, Long serviceId);

    Optional<BankServiceMapDB> findByBankIdAndServiceId(Long bankId, Long serviceId);

    Optional<BankServiceMapDB> findByServiceIdAndBankId(Long serviceId, Long bankId);

    List<BankServiceMapDB>findByBankId(Long bankId);
}
