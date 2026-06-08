package com.sarvatra.microservice.model;

import com.sarvatra.microservice.helper.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class FeatureMasterRequest {


    @Pattern(regexp = Constants.PRODUCT_ID_REGEX, message = "{INVALID_PRODUCT_ID}")
    private Long productId;


    @Pattern(regexp = Constants.FEATURE_NAME_REGEX, message = "{INVALID_FEATURE_NAME}")
    private String name;

    @Pattern(regexp = Constants.STATUS_REGEX, message = "{INVALID_STATUS}")
    private String status;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(@NotBlank(message = "{PRODUCT_ID_REQUIRED}") @Pattern(regexp = Constants.PRODUCT_ID_REGEX, message = "{INVALID_PRODUCT_ID}") Long productId) {
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
}
