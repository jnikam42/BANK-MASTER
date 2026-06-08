package com.sarvatra.microservice.model;


import java.time.LocalDateTime;

public class FeatureMappingRequest {

    private Long featureId;
    private String isActive;
    private LocalDateTime activationDate;
    private LocalDateTime deactivationDate;
    private String activationRemarks;
    private String deactivationRemarks;
    private String CreatedBy;

    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Long featureId) {
        this.featureId = featureId;
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

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }
}

