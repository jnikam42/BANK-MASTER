package com.sarvatra.microservice.db.repository;

import com.sarvatra.microservice.db.entity.BankProductMapDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankProductMapRepository extends JpaRepository<BankProductMapDB, Long> {
    Optional<BankProductMapDB> findByBankIdAndProductIdAndServiceMapId(Long bankId, Long productId, Long serviceMapId);

    List<BankProductMapDB> findByServiceMapIdAndBankId(Long serviceId, Long bankId);


    @Modifying
    @Transactional
    @Query("""
    UPDATE BankProductMapDB p
    SET p.isActive = :status,
        p.activationDate = CASE WHEN :status = 'Y' THEN CURRENT_TIMESTAMP ELSE p.activationDate END,
        p.deactivationDate = CASE WHEN :status = 'N' THEN CURRENT_TIMESTAMP ELSE p.deactivationDate END
    WHERE p.serviceMapId IN :serviceIds AND p.bankId = :bankId
""")
    int updateProductStatus(List<Long> serviceIds, Long bankId, String status);
}

