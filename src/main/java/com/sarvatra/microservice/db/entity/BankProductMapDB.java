package com.sarvatra.microservice.db.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = BankProductMapDB.TABLE_NAME)
public class BankProductMapDB {

    public static final String TABLE_NAME = "INT_BANK_PRODUCT_MAP";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BANK_ID", nullable = false)
    private Long bankId;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @Column(name = "SERVICE_MAP_ID", nullable = false)
    private Long serviceMapId;

    @Column(name = "IS_ACTIVE")
    private String isActive;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "ACTIVATION_DATE")
    private LocalDateTime activationDate;

    @Column(name = "ACTIVATION_REMARKS")
    private String activationRemarks;

    @Column(name = "DEACTIVATION_DATE")
    private LocalDateTime deactivationDate;

    @Column(name = "DEACTIVATION_REMARKS")
    private String deactivationRemarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getServiceMapId() {
        return serviceMapId;
    }

    public void setServiceMapId(Long serviceMapId) {
        this.serviceMapId = serviceMapId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDateTime activationDate) {
        this.activationDate = activationDate;
    }

    public String getActivationRemarks() {
        return activationRemarks;
    }

    public void setActivationRemarks(String activationRemarks) {
        this.activationRemarks = activationRemarks;
    }

    public LocalDateTime getDeactivationDate() {
        return deactivationDate;
    }

    public void setDeactivationDate(LocalDateTime deactivationDate) {
        this.deactivationDate = deactivationDate;
    }

    public String getDeactivationRemarks() {
        return deactivationRemarks;
    }

    public void setDeactivationRemarks(String deactivationRemarks) {
        this.deactivationRemarks = deactivationRemarks;
    }
}

