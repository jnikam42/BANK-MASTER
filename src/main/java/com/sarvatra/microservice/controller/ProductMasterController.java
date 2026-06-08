package com.sarvatra.microservice.controller;

import com.sarvatra.microservice.model.ProductMasterRequest;
import com.sarvatra.microservice.model.RestResponse;
import com.sarvatra.microservice.service.ProductMasterService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
public class ProductMasterController {

    private final ProductMasterService productMasterService;

    public ProductMasterController(ProductMasterService productMasterService) {
        this.productMasterService = productMasterService;
    }

    @PostMapping("/by-service")
    public RestResponse fetchProductsByServiceId(@RequestBody ProductMasterRequest request) {
        return productMasterService.fetchProductsByServiceId(request);
    }

    @PostMapping("/features-by-product")
    public RestResponse fetchProductsWithFeaturesByServiceId(@RequestBody ProductMasterRequest request) {
        return productMasterService.fetchProductsWithFeatureByServiceId(request);
    }
}
