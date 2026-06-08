package com.sarvatra.microservice.db.entity;


import jakarta.persistence.*;

@Entity
@Table(name = FeatureMasterDB.TABLE_NAME)
public class FeatureMasterDB {

    public static final String TABLE_NAME = "INT_FEATURE_MASTER";
    public static final String SEQ_NAME = TABLE_NAME + "_SEQ";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "FEATURE_ID")
    private Long Id;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "STATUS", nullable = false)
    private String status;


    public Long getFeatureId() {
        return Id;
    }

    public void setFeatureId(Long Id) {
        this.Id = Id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

