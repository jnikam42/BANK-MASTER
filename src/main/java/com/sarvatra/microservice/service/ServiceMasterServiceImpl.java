package com.sarvatra.microservice.service;

import com.sarvatra.microservice.db.entity.ServiceMasterDB;
import com.sarvatra.microservice.db.repository.ServiceMasterRepository;
import com.sarvatra.microservice.helper.Constants;
import com.sarvatra.microservice.helper.LogAdaptor;
import com.sarvatra.microservice.helper.LoggerUtil;
import com.sarvatra.microservice.model.RestResponse;
import com.sarvatra.microservice.model.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceMasterServiceImpl implements ServiceMasterService {

    private static final LogAdaptor LOG = LoggerUtil.getLogger("ServiceMasterServiceImpl");

    private final ServiceMasterRepository serviceMasterRepository;

    public ServiceMasterServiceImpl(ServiceMasterRepository serviceMasterRepository) {
        this.serviceMasterRepository = serviceMasterRepository;
    }

    @Override
    public RestResponse getAllServices() {
        List<ServiceMasterDB> services = serviceMasterRepository.findAll();

        if (services.isEmpty()) {
            LOG.warn("No services found in master table");
            return RestResponse.createResponse(Constants.SERVICE_NOT_FOUND_CODE, Constants.SERVICE_NOT_FOUND_MSG);
        }
        LOG.debug("Fetched {} services successfully", services.size());
        return new ServiceResponse(services);
    }

   }
