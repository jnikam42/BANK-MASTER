package com.sarvatra.microservice.model;

import com.sarvatra.microservice.db.entity.FeatureMasterDB;

import java.util.List;


public class FeatureResponse extends RestResponse {

    private List<FeatureMasterDB> features;

    public FeatureResponse(List<FeatureMasterDB> featureMasterList) {
        this.features = featureMasterList;
    }

    public List<FeatureMasterDB> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureMasterDB> features) {
        this.features = features;
    }
}


