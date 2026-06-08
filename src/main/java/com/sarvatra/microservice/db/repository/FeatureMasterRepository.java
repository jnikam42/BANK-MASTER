package com.sarvatra.microservice.db.repository;


import com.sarvatra.microservice.db.entity.FeatureMasterDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureMasterRepository extends JpaRepository<FeatureMasterDB, Long> {
    List<FeatureMasterDB> findByProductId(Long productId);
}
