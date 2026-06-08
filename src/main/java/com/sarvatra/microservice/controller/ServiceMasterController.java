package com.sarvatra.microservice.controller;

import com.sarvatra.microservice.model.RestResponse;
import com.sarvatra.microservice.service.ServiceMasterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
public class ServiceMasterController {


    private final ServiceMasterService serviceMasterService;

    public ServiceMasterController(ServiceMasterService serviceMasterService) {
        this.serviceMasterService = serviceMasterService;
    }

    @PostMapping("/fetch-services")
    public RestResponse getAllServices() {
        return serviceMasterService.getAllServices();

    }

}
