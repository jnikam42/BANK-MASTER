package com.sarvatra.microservice.model;

import java.time.LocalDateTime;

public class BankServiceMapDTO {

    private Long id;
    private Long bankId;
    private Long serviceId;
    private String serviceName;
    private String isActive;
    private String createdBy;
    private LocalDateTime createdDate;
    private LocalDateTime activationDate;
    private String activationRemarks;
    private LocalDateTime deactivationDate;
    private String deactivationRemarks;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

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

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
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

