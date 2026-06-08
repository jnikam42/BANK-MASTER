package com.sarvatra.microservice.convertor;

import com.sarvatra.microservice.db.entity.BankServiceMapDB;
import com.sarvatra.microservice.db.entity.ServiceMasterDB;
import com.sarvatra.microservice.db.repository.ServiceMasterRepository;
import com.sarvatra.microservice.model.BankServiceMapDTO;
import com.sarvatra.microservice.model.BankMappingRequest;
import com.sarvatra.microservice.model.ServiceMappingRequest;
import com.sarvatra.microservice.model.BankMappingResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class BankServiceMappingConverter {

    public static void setBankServiceData(BankServiceMapDB target, ServiceMasterDB serviceMaster, BankMappingRequest request) {
        target.setBankId(request.getBankId());
        target.setServiceId(serviceMaster.getServiceId());
        target.setIsActive(request.getServices().getIsActive());
        target.setCreatedBy(request.getCreatedBy());
        target.setCreatedDate(LocalDateTime.now());
        target.setActivationDate(request.getServices().getActivationDate());
        target.setDeactivationDate(request.getServices().getDeactivationDate());
        target.setActivationRemarks(request.getServices().getActivationRemarks());
        target.setDeactivationRemarks(request.getServices().getDeactivationRemarks());
    }

    public static void convertToEntity(BankServiceMapDB target, ServiceMappingRequest source, Long bankId, String createdBy) {
        target.setBankId(bankId);
        target.setServiceId(source.getServiceId());
        target.setIsActive(source.getIsActive());
        target.setCreatedBy(createdBy);
        target.setCreatedDate(LocalDateTime.now());
        target.setActivationDate(source.getActivationDate());
        target.setActivationRemarks(source.getActivationRemarks());
        target.setDeactivationDate(source.getDeactivationDate());
        target.setDeactivationRemarks(source.getDeactivationRemarks());
    }
public static void convertBankServicesToDto
        (List<BankServiceMapDB> services, BankMappingResponse response, ServiceMasterRepository serviceMasterRepo) {
        List<BankServiceMapDTO> bankServiceDTOS = new ArrayList<>();
        for (BankServiceMapDB service : services) {
            BankServiceMapDTO dto = new BankServiceMapDTO();
            dto.setId(service.getId());
            dto.setBankId(service.getBankId());
            dto.setServiceId(service.getServiceId());
            dto.setIsActive(service.getIsActive());
            dto.setActivationDate(service.getActivationDate());
            dto.setDeactivationDate(service.getDeactivationDate());
            dto.setActivationRemarks(service.getActivationRemarks());
            dto.setDeactivationRemarks(service.getDeactivationRemarks());
            dto.setCreatedDate(service.getCreatedDate());
            dto.setCreatedBy(service.getCreatedBy());
            dto.setServiceName(serviceMasterRepo.getNameById(service.getServiceId()));
            bankServiceDTOS.add(dto);
        }
        response.setBankMappingServices(bankServiceDTOS);
    }

}
