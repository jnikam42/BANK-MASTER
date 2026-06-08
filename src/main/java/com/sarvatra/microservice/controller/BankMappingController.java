package com.sarvatra.microservice.controller;

import com.sarvatra.microservice.model.ActivateServiceRequest;
import com.sarvatra.microservice.exception.ServiceException;
import com.sarvatra.microservice.model.RestResponse;
import com.sarvatra.microservice.model.BankMappingRequest;
import com.sarvatra.microservice.service.BankMappingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/bank-mapping")
public class BankMappingController {
    public static final String BANKID ="bankId";
    private final BankMappingService bankMappingService;

    public BankMappingController(BankMappingService bankMappingService) {
        this.bankMappingService = bankMappingService;
    }

    @PostMapping("/save")
    public RestResponse saveMappings(@RequestBody BankMappingRequest request) throws ServiceException {
        return bankMappingService.saveBankMappings(request);
    }

    @PostMapping("/mapped-bank-service")
    public RestResponse mappedBankService(@RequestBody BankMappingRequest request) {
        return bankMappingService.mappedBankService(request);
    }

    @PostMapping("/service/activate-deactivate")
    public RestResponse activateDeactivateService(@RequestBody ActivateServiceRequest request) {
        return bankMappingService.activateDeactivateService(request);
    }

    @PostMapping("/fetch-services-by-bankid")
    public RestResponse getServicesByBankId(@RequestBody Map<String, Long> request) {
        return bankMappingService.getServicesByBankId(request.get(BANKID));
    }

    @PostMapping("/map-and-update")
    public RestResponse mapAndUpdateServices(@RequestBody BankMappingRequest request) {
        return bankMappingService.mapAndUpdateServices(request);
    }


}
