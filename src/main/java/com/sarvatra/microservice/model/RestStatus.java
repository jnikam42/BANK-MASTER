package com.sarvatra.microservice.model;

public class RestStatus {
    private String responseCode = "000";
    private String responseMessage = "success";
    private String tranRefId;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getTranRefId() {
        return tranRefId;
    }

    public void setTranRefId(String tranRefId) {
        this.tranRefId = tranRefId;
    }


}
