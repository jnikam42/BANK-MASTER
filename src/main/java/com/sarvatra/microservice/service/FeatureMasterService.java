package com.sarvatra.microservice.service;

import com.sarvatra.microservice.model.RestResponse;
import com.sarvatra.microservice.model.FeaturesMasterRequest;


public interface FeatureMasterService {
    RestResponse getFeaturesByProductId(FeaturesMasterRequest request);
}
