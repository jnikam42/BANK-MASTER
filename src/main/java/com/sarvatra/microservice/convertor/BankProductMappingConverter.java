package com.sarvatra.microservice.convertor;

import com.sarvatra.microservice.db.entity.BankProductMapDB;
import com.sarvatra.microservice.model.BankMappingRequest;
import com.sarvatra.microservice.model.ProductMappingRequest;

import java.time.LocalDateTime;

public  final class BankProductMappingConverter {
    public static void setProductMappingData(BankProductMapDB target, ProductMappingRequest source, BankMappingRequest request, Long serviceMapId) {
        target.setBankId(request.getBankId());
        target.setProductId(source.getProductId());
        target.setServiceMapId(serviceMapId);
        target.setIsActive(source.getIsActive());
        target.setCreatedBy(request.getCreatedBy());
        target.setCreatedDate(LocalDateTime.now());
        target.setActivationDate(source.getActivationDate());
        target.setDeactivationDate(source.getDeactivationDate());
        target.setActivationRemarks(source.getActivationRemarks());
        target.setDeactivationRemarks(source.getDeactivationRemarks());
    }
}
