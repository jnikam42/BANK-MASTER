package com.sarvatra.microservice.model;

import java.util.List;


public class ProductWithFeatureResponse extends RestResponse {

    private List<ProductWithFeatureDTO> features;

    public ProductWithFeatureResponse(List<ProductWithFeatureDTO> featureMasterList) {
        this.features = featureMasterList;
    }

    public List<ProductWithFeatureDTO> getFeatures() {
        return features;
    }

    public void setFeatures(List<ProductWithFeatureDTO> features) {
        this.features = features;
    }
}


