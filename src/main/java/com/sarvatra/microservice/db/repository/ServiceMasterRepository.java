package com.sarvatra.microservice.db.repository;

import com.sarvatra.microservice.db.entity.ServiceMasterDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceMasterRepository extends JpaRepository<ServiceMasterDB, Long> {

    @Query("SELECT s.name FROM ServiceMasterDB s WHERE s.Id = :serviceId")
    String getNameById(Long serviceId);
}
