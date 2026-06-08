package com.sarvatra.microservice.helper;


public final class Constants {


    public static final String ACTIVE_FLAG = "Y";
    public static final String INACTIVE_FLAG = "N";
    public static final String ACTIVATED_SERVICE_MSG = "Activated Service";
    public static final String DEACTIVATED_SERVICE_MSG = "Deactivated Service";
    public static final String ACTIVATED_PRODUCT_MSG = "Activated Product";
    public static final String DEACTIVATED_PRODUCT_MSG = "Deactivated Product";
    public static final String ACTIVATED_FEATURE_MSG = "Activated Feature";
    public static final String DEACTIVATED_FEATURE_MSG = "Deactivated Feature";
    public static final String SUCCESS_CODE = "000";
    public static final String BANK_MAPPING_SAVE_SUCCESS = "Bank mapping saved successfully";
    public static final String INVALID_SERVICE_ID_MSG = " Service Id is Not Avaliable or Invalid Service Id";
    public static final String PRODUCT_ID_REGEX = "^[1-9][0-9]*$";
    public static final String FEATURE_NAME_REGEX = "^[a-zA-Z0-9 _-]{0,100}$";
    public static final String STATUS_REGEX = "^[YyNn]$";
    public static final String SERVICE_ID_REGEX = "^[1-9][0-9]*$";
    public static final String PRODUCT_NAME_REGEX = "^[a-zA-Z0-9 _-]{0,100}$";
    public static final String SERVICE_NOT_FOUND_CODE = "701";
    public static final String SERVICE_NOT_FOUND_MSG = "No services found.";
    public static final String PRODUCT_NOT_FOUND_CODE = "702";
    public static final String PRODUCT_NOT_FOUND_MSG = "No products found for the given service Id.";
    public static final String FEATURE_NOT_FOUND_CODE = "703";
    public static final String FEATURE_NOT_FOUND_MSG = "No features found for the given Product.";
    public static final String SERVICE_ACTIVATION_MSG = "Service, associated products, and features updated successfully.";
    public static final String INVALID_SERVICE_OR_BANK_ID = "704";
    public static final String INVALID_SERVICE_OR_BANK_ID_MSG = "The provided bankId or serviceId does not exist in the system.";
    public static final String INVALID_FLAG_CODE = "705";
    public static final String INVALID_FLAG_MSG = "isActive flag must be either 'Y' (activate) or 'N' (deactivate).";
    public static final String INVALID_REQUEST = "706";
    public static final String INVALID_REQUEST_MSG = "bankId, serviceId, and isActive flag are required.";
    public static final String SERVICE_MAPPING_NOT_FOUND = "Service Mapping not found for the given bankId and serviceId.";
    public static final String SERVICE_MAPPED_MSG= "Service Services Successfully Mapped.";
    public static final String DUBLICATE_BANKID_SERVICEID_CODE= "707";
    public static final String DUBLICATE_BANKID_SERVICEID_MSG= "BankId and ServiceId mapping already exists.";

    private Constants() {

    }


}
