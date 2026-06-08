package com.sarvatra.microservice.model;

import java.time.LocalDateTime;
import java.util.List;

public class BankMappingRequest {

    private Long bankId;
    private String createdBy;
    private LocalDateTime createdDate;
    private ServiceMappingRequest services;
    private List<ServiceMappingRequest> servicesList;


    public List<ServiceMappingRequest> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<ServiceMappingRequest> servicesList) {
        this.servicesList = servicesList;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
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

    public ServiceMappingRequest getServices() {
        return services;
    }

    public void setServices(ServiceMappingRequest services) {
        this.services = services;
    }
}

