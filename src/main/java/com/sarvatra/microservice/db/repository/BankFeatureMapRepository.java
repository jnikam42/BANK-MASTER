package com.sarvatra.microservice.db.repository;

import com.sarvatra.microservice.db.entity.BankFeatureMapDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankFeatureMapRepository extends JpaRepository<BankFeatureMapDB, Long> {
    Optional<BankFeatureMapDB> findByBankIdAndFeatureIdAndProductMapId(Long bankId, Long featureId, Long productMapId);

    List<BankFeatureMapDB> findByProductMapId(Long productMapId);

    @Modifying
    @Transactional
    @Query("""
    UPDATE BankFeatureMapDB f
    SET f.isActive = :status,
        f.activationDate = CASE WHEN :status = 'Y' THEN CURRENT_TIMESTAMP ELSE f.activationDate END,
        f.deactivationDate = CASE WHEN :status = 'N' THEN CURRENT_TIMESTAMP ELSE f.deactivationDate END
    WHERE f.productMapId IN (
        SELECT p.id FROM BankProductMapDB p WHERE p.serviceMapId IN :serviceIds
    )
""")
    int updateFeatureStatus(List<Long> serviceIds, String status);
}

