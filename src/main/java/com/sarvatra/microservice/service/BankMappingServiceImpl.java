package com.sarvatra.microservice.service;

import com.sarvatra.microservice.ConfigService;
import com.sarvatra.microservice.convertor.BankFeatureMappingConvertor;
import com.sarvatra.microservice.convertor.BankProductMappingConverter;
import com.sarvatra.microservice.convertor.BankServiceMappingConverter;
import com.sarvatra.microservice.db.entity.*;
import com.sarvatra.microservice.db.repository.*;
import com.sarvatra.microservice.model.ActivateServiceRequest;
import com.sarvatra.microservice.exception.ServiceException;
import com.sarvatra.microservice.helper.Constants;
import com.sarvatra.microservice.helper.LogAdaptor;
import com.sarvatra.microservice.helper.LoggerUtil;
import com.sarvatra.microservice.model.RestResponse;
import com.sarvatra.microservice.model.BankMappingRequest;
import com.sarvatra.microservice.model.FeatureMappingRequest;
import com.sarvatra.microservice.model.ProductMappingRequest;
import com.sarvatra.microservice.model.ServiceMappingRequest;
import com.sarvatra.microservice.model.BankMappingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class BankMappingServiceImpl implements BankMappingService {


    private static final LogAdaptor LOG = LoggerUtil.getLogger("BankMappingServiceImpl");
    private static final Logger log = LoggerFactory.getLogger(BankMappingServiceImpl.class);
    private final ConfigService configService;
    private final BankServiceMapRepository bankServiceRepo;
    private final BankProductMapRepository bankProductRepo;
    private final BankFeatureMapRepository bankFeatureRepo;
    private final ServiceMasterRepository serviceMasterRepo;
    private final ProductMasterRepository productMasterRepo;
    private final FeatureMasterRepository featureMasterRepo;

    public BankMappingServiceImpl(ConfigService configService, BankServiceMapRepository bankServiceRepo, BankProductMapRepository bankProductRepo, BankFeatureMapRepository bankFeatureRepo, ServiceMasterRepository serviceMasterRepo, ProductMasterRepository productMasterRepo, FeatureMasterRepository featureMasterRepo) {
        this.configService = configService;
        this.bankServiceRepo = bankServiceRepo;
        this.bankProductRepo = bankProductRepo;
        this.bankFeatureRepo = bankFeatureRepo;
        this.serviceMasterRepo = serviceMasterRepo;
        this.productMasterRepo = productMasterRepo;
        this.featureMasterRepo = featureMasterRepo;
    }

    @Override
    public RestResponse saveBankMappings(BankMappingRequest request) throws ServiceException {
        Long bankId = request.getBankId();
        LOG.info("Starting bank mapping process for bankId={}", bankId);

        ServiceMasterDB serviceMaster = serviceMasterRepo.findById(request.getServices().getServiceId()).orElseThrow(() -> new ServiceException(Constants.INVALID_SERVICE_ID_MSG));
        LOG.debug("Service ID={} validated successfully.", serviceMaster.getServiceId());

        BankServiceMapDB serviceMap = bankServiceRepo.findByBankIdAndServiceId(bankId, serviceMaster.getServiceId()).orElse(new BankServiceMapDB());
        BankServiceMappingConverter.setBankServiceData(serviceMap, serviceMaster, request);
        Long serviceMapId = bankServiceRepo.save(serviceMap).getId();
        LOG.debug("Service mapping saved with ID={}", serviceMapId);

        List<Long> productIds = request.getServices().getProducts().stream().map(ProductMappingRequest::getProductId).distinct().toList();
        List<ProductMasterDB> productMasterList = productMasterRepo.findAllById(productIds);

        if (productMasterList.size() != productIds.size()) {
            throw new ServiceException(Constants.PRODUCT_NOT_FOUND_MSG);
        }
        for (ProductMappingRequest productRequest : request.getServices().getProducts()) {
            BankProductMapDB productMap = bankProductRepo.findByBankIdAndProductIdAndServiceMapId(bankId, productRequest.getProductId(), serviceMapId).orElse(new BankProductMapDB());
            BankProductMappingConverter.setProductMappingData(productMap, productRequest, request, serviceMapId);
            Long productMapId = bankProductRepo.save(productMap).getId();
            LOG.debug("Product mapping saved with ID={}", productMapId);

            List<Long> featureIds = productRequest.getFeatures().stream().map(FeatureMappingRequest::getFeatureId).distinct().toList();
            List<FeatureMasterDB> featureMasterList = featureMasterRepo.findAllById(featureIds);

            if (featureMasterList.size() != featureIds.size()) {
                throw new ServiceException(Constants.FEATURE_NOT_FOUND_MSG);
            }

            for (FeatureMappingRequest featureRequest : productRequest.getFeatures()) {
                BankFeatureMapDB featureMap = bankFeatureRepo.findByBankIdAndFeatureIdAndProductMapId(bankId, featureRequest.getFeatureId(), productMapId).orElse(new BankFeatureMapDB());

                BankFeatureMappingConvertor.setFeatureMappingData(featureMap, featureRequest, request, productMapId);
                Long featureMapId = bankFeatureRepo.save(featureMap).getId();
                LOG.debug("Feature mapping saved with ID={}", featureMapId);
            }
        }

        LOG.debug("Bank mapping process completed successfully for bankId={}", bankId);
        return RestResponse.createResponse(Constants.SUCCESS_CODE, Constants.BANK_MAPPING_SAVE_SUCCESS);
    }

    @Override
    public RestResponse activateDeactivateService(ActivateServiceRequest request) {
        if (request.getBankId() == null || request.getServiceId() == null || request.getIsActive() == null) {
            return RestResponse.createResponse(Constants.INVALID_REQUEST, Constants.INVALID_REQUEST_MSG);
        }
        if (!request.getIsActive().equalsIgnoreCase(Constants.ACTIVE_FLAG) && !request.getIsActive().equalsIgnoreCase(Constants.INACTIVE_FLAG)) {
            return RestResponse.createResponse(Constants.INVALID_FLAG_CODE, Constants.INVALID_FLAG_MSG);
        }
        boolean isServiceExists = bankServiceRepo.existsByBankIdAndServiceId(request.getBankId(), request.getServiceId());
        if (!isServiceExists) {
            return RestResponse.createResponse(Constants.INVALID_SERVICE_OR_BANK_ID, Constants.INVALID_SERVICE_OR_BANK_ID_MSG);
        }
        Optional<BankServiceMapDB> bankServiceMapOptional = bankServiceRepo.findByServiceIdAndBankId(request.getServiceId(), request.getBankId());
        if (bankServiceMapOptional.isPresent()) {
            BankServiceMapDB bankServiceMap = bankServiceMapOptional.get();
            String isActive = request.getIsActive();
            bankServiceMap.setIsActive(isActive.toUpperCase());
            if (isActive.equalsIgnoreCase(Constants.ACTIVE_FLAG)) {
                bankServiceMap.setActivationDate(LocalDateTime.now());
                bankServiceMap.setActivationRemarks(Constants.ACTIVATED_SERVICE_MSG);
            } else {
                bankServiceMap.setDeactivationDate(LocalDateTime.now());
                bankServiceMap.setDeactivationRemarks(Constants.DEACTIVATED_SERVICE_MSG);
            }
            BankServiceMapDB updatedService = bankServiceRepo.save(bankServiceMap);
            List<BankProductMapDB> productMappings = bankProductRepo.findByServiceMapIdAndBankId(updatedService.getId(), updatedService.getBankId());
            for (BankProductMapDB productMapping : productMappings) {
                productMapping.setIsActive(isActive.toUpperCase());
                if (isActive.equalsIgnoreCase(Constants.ACTIVE_FLAG)) {
                    productMapping.setActivationDate(LocalDateTime.now());
                    productMapping.setActivationRemarks(Constants.ACTIVATED_PRODUCT_MSG);
                } else {
                    productMapping.setDeactivationDate(LocalDateTime.now());
                    productMapping.setDeactivationRemarks(Constants.DEACTIVATED_PRODUCT_MSG);
                }
                BankProductMapDB updatedProduct = bankProductRepo.save(productMapping);
                List<BankFeatureMapDB> featureMappings = bankFeatureRepo.findByProductMapId(updatedProduct.getId());
                for (BankFeatureMapDB featureMapping : featureMappings) {
                    featureMapping.setIsActive(isActive.toUpperCase());
                    if (isActive.equalsIgnoreCase(Constants.ACTIVE_FLAG)) {
                        featureMapping.setActivationDate(LocalDateTime.now());
                        featureMapping.setActivationRemarks(Constants.ACTIVATED_FEATURE_MSG);
                    } else {
                        featureMapping.setDeactivationDate(LocalDateTime.now());
                        featureMapping.setDeactivationRemarks(Constants.DEACTIVATED_FEATURE_MSG);
                    }
                    bankFeatureRepo.save(featureMapping);
                }
            }
            return RestResponse.createResponse(Constants.SUCCESS_CODE, Constants.SERVICE_ACTIVATION_MSG);
        }
        return RestResponse.createResponse(Constants.INVALID_REQUEST, Constants.SERVICE_MAPPING_NOT_FOUND);
    }

    @Override
    public RestResponse mappedBankService(BankMappingRequest request) {
        List<BankServiceMapDB> data = new ArrayList<>();
        for (ServiceMappingRequest source : request.getServicesList()) {
            boolean exists = bankServiceRepo.existsByBankIdAndServiceId(request.getBankId(), source.getServiceId());
            if (exists) {
                continue;
//          return RestResponse.createResponse(Constants.DUBLICATE_BANKID_SERVICEID_CODE, Constants.DUBLICATE_BANKID_SERVICEID_MSG);
            }
            BankServiceMapDB target = new BankServiceMapDB();
            BankServiceMappingConverter.convertToEntity(target, source, request.getBankId(), request.getCreatedBy());
            data.add(target);
        }
        if (!data.isEmpty()) {
            bankServiceRepo.saveAll(data);
        }
        for (ServiceMappingRequest source : request.getServicesList()) {
            ActivateServiceRequest req = new ActivateServiceRequest();
            req.setServiceId(source.getServiceId());
            req.setIsActive(source.getIsActive());
            req.setBankId(request.getBankId());
            RestResponse restResponse = activateDeactivateService(req);
            log.info("res", restResponse);
        }
        return RestResponse.createResponse(Constants.SUCCESS_CODE, "updated success");
    }

    @Override
    public RestResponse getServicesByBankId(Long bankId) {
        List<BankServiceMapDB> services = bankServiceRepo.findByBankId(bankId);
        if (services.isEmpty()) {
            LOG.warn("No services found in master table");
            return RestResponse.createResponse(Constants.SERVICE_NOT_FOUND_CODE, Constants.SERVICE_NOT_FOUND_MSG);
        }
        LOG.debug("Fetched {} services successfully", services.size());
        BankMappingResponse response = new BankMappingResponse();
        BankServiceMappingConverter.convertBankServicesToDto(services, response, serviceMasterRepo);
        return response;
    }


    @Override
    public RestResponse mapAndUpdateServices(BankMappingRequest request) {

        if (request.getBankId() == null || request.getServicesList() == null) {
            log.debug("Invalid request received in mapAndUpdateServices. request={}", request);
            return RestResponse.createResponse(Constants.INVALID_REQUEST, Constants.INVALID_REQUEST_MSG);
        }

        List<BankServiceMapDB> savedServices = saveServices(request);
        log.debug("Saved services count={} for bankId={}", savedServices.size(), request.getBankId());

        Map<String, List<Long>> grouped = savedServices.stream().collect(Collectors.groupingBy(BankServiceMapDB::getIsActive, Collectors.mapping(BankServiceMapDB::getId, Collectors.toList())));

        for (Map.Entry<String, List<Long>> entry : grouped.entrySet())
            updateProductsAndFeatures(entry.getValue(), request.getBankId(), entry.getKey());
        log.info("mapAndUpdateServices completed successfully for bankId={}", request.getBankId());
        return RestResponse.createResponse(Constants.SUCCESS_CODE, Constants.SERVICE_ACTIVATION_MSG);
    }


    @Transactional
    public List<BankServiceMapDB> saveServices(BankMappingRequest request) {

        LocalDateTime now = LocalDateTime.now();
        List<BankServiceMapDB> list = new ArrayList<>();
        log.debug("saveServices started for bankId={}", request.getBankId());
        for (ServiceMappingRequest source : request.getServicesList()) {

            Optional<BankServiceMapDB> optional = bankServiceRepo.findByServiceIdAndBankId(source.getServiceId(), request.getBankId());
            BankServiceMapDB entity;

            if (optional.isPresent()) {
                entity = optional.get();
                log.debug("Existing mapping found for serviceId={}", source.getServiceId());
            } else {
                log.debug("Creating new mapping for serviceId={}", source.getServiceId());
                entity = new BankServiceMapDB();
                entity.setBankId(request.getBankId());
                entity.setServiceId(source.getServiceId());
                entity.setCreatedBy(request.getCreatedBy());
                entity.setCreatedDate(now);
            }
            String status = source.getIsActive().toUpperCase();
            entity.setIsActive(status);

            if (Constants.ACTIVE_FLAG.equalsIgnoreCase(status)) {
                entity.setActivationDate(now);
                entity.setActivationRemarks(Constants.ACTIVATED_SERVICE_MSG);
            } else {
                entity.setDeactivationDate(now);
                entity.setDeactivationRemarks(Constants.DEACTIVATED_SERVICE_MSG);
            }

            list.add(entity);
        }
        List<BankServiceMapDB> saved = bankServiceRepo.saveAll(list);
        log.debug("Services  saved for bankId={}", request.getBankId());
        return saved;
    }

     @Transactional
    public void updateProductsAndFeatures(List<Long> serviceIds, Long bankId, String status) {
        if (serviceIds == null || serviceIds.isEmpty()) {
            log.warn("No serviceIds provided. Skipping update. bankId={}, status={}", bankId, status);
            return;
        }
        log.debug("Updating products. bankId={}, status={}, serviceIds={}", bankId, status, serviceIds);
            bankProductRepo.updateProductStatus(serviceIds, bankId, status);
            log.debug("Product update successful for bankId={}", bankId);

            bankFeatureRepo.updateFeatureStatus(serviceIds, status);
            log.debug("Feature update successful for serviceIds={}", serviceIds);
    }


}