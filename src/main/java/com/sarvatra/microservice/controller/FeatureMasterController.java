package com.sarvatra.microservice.controller;

import com.sarvatra.microservice.model.RestResponse;
import com.sarvatra.microservice.model.FeaturesMasterRequest;
import com.sarvatra.microservice.service.FeatureMasterService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/features")
public class FeatureMasterController {

    private final FeatureMasterService featureMasterService;

    public FeatureMasterController(FeatureMasterService featureMasterService) {
        this.featureMasterService = featureMasterService;
    }

    @PostMapping("/by-product")
    public RestResponse getFeaturesByProductId(@RequestBody @Valid FeaturesMasterRequest request) {
        return featureMasterService.getFeaturesByProductId(request);
    }
}
