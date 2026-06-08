package com.sarvatra.microservice.service;

import com.sarvatra.microservice.model.ActivateServiceRequest;
import com.sarvatra.microservice.exception.ServiceException;
import com.sarvatra.microservice.model.RestResponse;
import com.sarvatra.microservice.model.BankMappingRequest;


public interface BankMappingService {
    RestResponse saveBankMappings(BankMappingRequest request) throws ServiceException;

    RestResponse activateDeactivateService(ActivateServiceRequest request);

    RestResponse mappedBankService(BankMappingRequest request);

    RestResponse getServicesByBankId(Long bankId);

    RestResponse mapAndUpdateServices(BankMappingRequest request);
}

