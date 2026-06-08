package com.sarvatra.microservice.db.repository;


import com.sarvatra.microservice.db.entity.ProductMasterDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMasterRepository extends JpaRepository<ProductMasterDB, Long> {
    List<ProductMasterDB> findByServiceId(Long serviceId);
}
