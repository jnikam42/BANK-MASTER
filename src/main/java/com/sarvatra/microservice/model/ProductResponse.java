package com.sarvatra.microservice.model;

import com.sarvatra.microservice.db.entity.ProductMasterDB;

import java.util.List;


public class ProductResponse extends RestResponse {

    private List<ProductMasterDB> products;

    public ProductResponse(List<ProductMasterDB> productMasterList) {
        this.products = productMasterList;
    }

    public List<ProductMasterDB> getProducts() {
        return products;
    }

    public void setProducts(List<ProductMasterDB> products) {
        this.products = products;
    }
}


