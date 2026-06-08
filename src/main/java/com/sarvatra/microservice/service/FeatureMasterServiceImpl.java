package com.sarvatra.microservice.service;

import com.sarvatra.microservice.db.entity.FeatureMasterDB;
import com.sarvatra.microservice.db.repository.FeatureMasterRepository;
import com.sarvatra.microservice.helper.Constants;
import com.sarvatra.microservice.helper.LogAdaptor;
import com.sarvatra.microservice.helper.LoggerUtil;
import com.sarvatra.microservice.model.RestResponse;
import com.sarvatra.microservice.model.FeaturesMasterRequest;
import com.sarvatra.microservice.model.FeatureResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureMasterServiceImpl implements FeatureMasterService {

    private static final LogAdaptor LOG = LoggerUtil.getLogger("FeatureMasterServiceImpl");


    private final FeatureMasterRepository featureMasterRepository;

    public FeatureMasterServiceImpl(FeatureMasterRepository featureMasterRepository) {
        this.featureMasterRepository = featureMasterRepository;
    }

    @Override
    public RestResponse getFeaturesByProductId(FeaturesMasterRequest request) {
        Long productId = Long.valueOf(request.getProductId());

        List<FeatureMasterDB> featureMasters = featureMasterRepository.findByProductId(productId);

        if (featureMasters.isEmpty()) {
            LOG.war("No features found for productId={}", productId);
            return RestResponse.createResponse(Constants.FEATURE_NOT_FOUND_CODE, Constants.FEATURE_NOT_FOUND_MSG);
        }
        LOG.debug("Fetched {} features for productId={}", featureMasters.size(), productId);
        return new FeatureResponse(featureMasters);
    }

}

