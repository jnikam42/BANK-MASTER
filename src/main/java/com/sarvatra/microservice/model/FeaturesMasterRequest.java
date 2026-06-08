package com.sarvatra.microservice.model;


import com.sarvatra.microservice.helper.Constants;
import jakarta.validation.constraints.Pattern;

public class FeaturesMasterRequest {


    @Pattern(regexp = Constants.PRODUCT_ID_REGEX, message = "{INVALID_PRODUCT_ID}")
    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
