package com.sarvatra.microservice.model;

import java.util.List;

public class ProductWithFeatureDTO {

    private Long productId;
    private String name;
    private String status;
    private List<FeatureDTO> features;

    public ProductWithFeatureDTO() {
    }

    public ProductWithFeatureDTO(Long productId, String name, String status, List<FeatureDTO> features) {
        this.productId = productId;
        this.name = name;
        this.status = status;
        this.features = features;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public List<FeatureDTO> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureDTO> features) {
        this.features = features;
    }
}
