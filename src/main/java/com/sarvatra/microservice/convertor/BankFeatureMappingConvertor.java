package com.sarvatra.microservice.convertor;

import com.sarvatra.microservice.db.entity.BankFeatureMapDB;
import com.sarvatra.microservice.model.BankMappingRequest;
import com.sarvatra.microservice.model.FeatureMappingRequest;

import java.time.LocalDateTime;

public final class BankFeatureMappingConvertor{
    public static void setFeatureMappingData(BankFeatureMapDB target, FeatureMappingRequest source, BankMappingRequest request, Long productMapId) {
        target.setBankId(request.getBankId());
        target.setProductMapId(productMapId);
        target.setFeatureId(source.getFeatureId());
        target.setIsActive(source.getIsActive());
        target.setCreatedBy(request.getCreatedBy());
        target.setCreatedDate(LocalDateTime.now());
        target.setActivationDate(source.getActivationDate());
        target.setDeactivationDate(source.getDeactivationDate());
        target.setActivationRemarks(source.getActivationRemarks());
        target.setDeactivationRemarks(source.getDeactivationRemarks());
    }
}
