package com.sarvatra.microservice.model;

import java.time.LocalDateTime;
import java.util.List;

public class ServiceMappingRequest {

    private Long serviceId;
    private String isActive;
    private String serviceName;
    private LocalDateTime activationDate;
    private LocalDateTime deactivationDate;
    private String activationRemarks;
    private String deactivationRemarks;
    private List<ProductMappingRequest> products;

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

    public LocalDateTime getActivationDate() {
        return activationDate;
    }

    public String getServiceName() {return serviceName;}

    public void setServiceName(String serviceName) {this.serviceName = serviceName;}

    public void setActivationDate(LocalDateTime activationDate) {
        this.activationDate = activationDate;
    }

    public LocalDateTime getDeactivationDate() {
        return deactivationDate;
    }

    public void setDeactivationDate(LocalDateTime deactivationDate) {
        this.deactivationDate = deactivationDate;
    }

    public String getActivationRemarks() {
        return activationRemarks;
    }

    public void setActivationRemarks(String activationRemarks) {
        this.activationRemarks = activationRemarks;
    }

    public String getDeactivationRemarks() {
        return deactivationRemarks;
    }

    public void setDeactivationRemarks(String deactivationRemarks) {
        this.deactivationRemarks = deactivationRemarks;
    }

    public List<ProductMappingRequest> getProducts() {
        return products;
    }

    public void setProducts(List<ProductMappingRequest> products) {
        this.products = products;
    }

}

