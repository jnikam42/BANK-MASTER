package com.sarvatra.microservice.model;

public class FeatureDTO {

    private Long featureId;
    private String name;
    private String status;

    public FeatureDTO(Long featureId, String name, String status) {
        this.featureId = featureId;
        this.name = name;
        this.status = status;
    }

    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Long featureId) {
        this.featureId = featureId;
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
