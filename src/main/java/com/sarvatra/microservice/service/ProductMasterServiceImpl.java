package com.sarvatra.microservice.service;


import com.sarvatra.microservice.db.entity.FeatureMasterDB;
import com.sarvatra.microservice.db.entity.ProductMasterDB;
import com.sarvatra.microservice.db.repository.FeatureMasterRepository;
import com.sarvatra.microservice.db.repository.ProductMasterRepository;
import com.sarvatra.microservice.model.FeatureDTO;
import com.sarvatra.microservice.model.ProductWithFeatureDTO;
import com.sarvatra.microservice.helper.Constants;
import com.sarvatra.microservice.helper.LogAdaptor;
import com.sarvatra.microservice.helper.LoggerUtil;
import com.sarvatra.microservice.model.ProductMasterRequest;
import com.sarvatra.microservice.model.RestResponse;
import com.sarvatra.microservice.model.ProductWithFeatureResponse;
import com.sarvatra.microservice.model.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMasterServiceImpl implements ProductMasterService {

    private static final LogAdaptor LOG = LoggerUtil.getLogger("ProductMasterServiceImpl");

    private final ProductMasterRepository productMasterRepository;
    private final FeatureMasterRepository featureMasterRepository;

    public ProductMasterServiceImpl(ProductMasterRepository productMasterRepository, FeatureMasterRepository featureMasterRepository) {
        this.productMasterRepository = productMasterRepository;
        this.featureMasterRepository = featureMasterRepository;
    }

    @Override
    public RestResponse fetchProductsByServiceId(ProductMasterRequest request) {
        Long serviceId = Long.valueOf(request.getServiceId());
        List<ProductMasterDB> productMaster = productMasterRepository.findByServiceId(serviceId);
        if (productMaster.isEmpty()) {
            LOG.war("No products found for serviceId={}", serviceId);

            return RestResponse.createResponse(Constants.PRODUCT_NOT_FOUND_CODE, Constants.PRODUCT_NOT_FOUND_MSG);
        }
        LOG.info("Fetched {} products for serviceId={}", productMaster.size(), serviceId);
        return new ProductResponse(productMaster);
    }


    @Override
    public RestResponse fetchProductsWithFeatureByServiceId(ProductMasterRequest request) {

        Long serviceId = Long.valueOf(request.getServiceId());

        List<ProductMasterDB> products = productMasterRepository.findByServiceId(serviceId);

        if (products.isEmpty()) {
            LOG.war("No products found for serviceId={}", serviceId);
            return RestResponse.createResponse(Constants.PRODUCT_NOT_FOUND_CODE, Constants.PRODUCT_NOT_FOUND_MSG);
        }

        List<ProductWithFeatureDTO> responseList = products.stream().map(product -> {

            List<FeatureMasterDB> features = featureMasterRepository.findByProductId(product.getProductId());

            List<FeatureDTO> featureDTOList = features.stream().map(f -> new FeatureDTO(f.getFeatureId(), f.getName(), f.getStatus())).toList();

            return new ProductWithFeatureDTO(product.getProductId(), product.getName(), product.getStatus(), featureDTOList);

        }).toList();
        LOG.debug("Fetched {} products with features for serviceId={}", responseList.size(), serviceId);
        return new ProductWithFeatureResponse(responseList);
    }


}
