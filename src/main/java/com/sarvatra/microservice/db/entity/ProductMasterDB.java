package com.sarvatra.microservice.db.entity;

import jakarta.persistence.*;

@Entity
@Table(name = ProductMasterDB.TABLE_NAME)
public class ProductMasterDB {

    public static final String TABLE_NAME = "INT_PRODUCT_MASTER";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "PRODUCT_ID")
    private Long Id;

    @Column(name = "SERVICE_ID", nullable = false)
    private Long serviceId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "STATUS", nullable = false)
    private String status;

    public Long getProductId() {
        return Id;
    }

    public void setProductId(Long Id) {
        this.Id = Id;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
