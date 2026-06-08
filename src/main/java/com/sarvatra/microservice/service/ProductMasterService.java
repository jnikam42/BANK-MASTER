package com.sarvatra.microservice.service;

import com.sarvatra.microservice.model.ProductMasterRequest;
import com.sarvatra.microservice.model.RestResponse;


public interface ProductMasterService {
    RestResponse fetchProductsByServiceId(ProductMasterRequest request);
    RestResponse fetchProductsWithFeatureByServiceId(ProductMasterRequest request) ;
    }
