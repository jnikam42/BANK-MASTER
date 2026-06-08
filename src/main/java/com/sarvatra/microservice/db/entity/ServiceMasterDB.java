package com.sarvatra.microservice.db.entity;

import jakarta.persistence.*;

@Entity
@Table(name = ServiceMasterDB.TABLE_NAME)
public class ServiceMasterDB {

    public static final String TABLE_NAME = "INT_SERVICE_MASTER";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";
    public static final String SERVICE_ID = "SERVICE_ID";
    public static final String SEQ_GEN = "service_master_seq_gen";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_GEN)
    @SequenceGenerator(name = SEQ_GEN, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = SERVICE_ID)
    private Long Id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "STATUS", nullable = false)
    private String status;

    public Long getServiceId() {
        return Id;
    }

    public void setServiceId(Long id) {
        this.Id = id;
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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
