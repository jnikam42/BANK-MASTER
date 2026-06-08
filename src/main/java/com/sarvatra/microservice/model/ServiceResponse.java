package com.sarvatra.microservice.model;

import com.sarvatra.microservice.db.entity.ServiceMasterDB;

import java.util.List;


public class ServiceResponse extends RestResponse {

    private List<ServiceMasterDB> services;

    public ServiceResponse(List<ServiceMasterDB> serviceMasterList) {
        this.services = serviceMasterList;
    }

    public List<ServiceMasterDB> getServices() {
        return services;
    }

    public void setServices(List<ServiceMasterDB> services) {
        this.services = services;
    }
}


