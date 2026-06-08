package com.sarvatra.microservice.model;

import java.time.LocalDateTime;
import java.util.List;

public class ProductMappingRequest {

    private Long productId;
    private String isActive;
    private LocalDateTime activationDate;
    private LocalDateTime deactivationDate;
    private String activationRemarks;
    private String deactivationRemarks;
    private String CreatedBy;

    private List<FeatureMappingRequest> features;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public List<FeatureMappingRequest> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureMappingRequest> features) {
        this.features = features;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }
}

