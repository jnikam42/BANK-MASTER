package com.sarvatra.microservice.model;

import com.sarvatra.microservice.helper.Constants;
import jakarta.validation.constraints.Pattern;

public class ProductMasterRequest {


    @Pattern(regexp = Constants.SERVICE_ID_REGEX, message = "{INVALID_SERVICE_ID}")
    private Long serviceId;


    @Pattern(regexp = Constants.PRODUCT_NAME_REGEX, message = "{INVALID_PRODUCT_NAME}")
    private String name;

    @Pattern(regexp = Constants.STATUS_REGEX, message = "{INVALID_STATUS}")
    private String status;

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
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
