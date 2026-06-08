package com.sarvatra.microservice.model;

public class RestResponse {
    private RestStatus responseStatus = new RestStatus();

    public static RestResponse setResponse(String responseCode, String responseMessage) {
        RestResponse response = new RestResponse();
        response.getResponseStatus().setResponseCode(responseCode);
        response.getResponseStatus().setResponseMessage(responseMessage);
        return response;
    }

    public static RestResponse createSuccessResponse() {
        RestResponse response = new RestResponse();
        RestStatus responseStatus = new RestStatus();
        response.setResponseStatus(responseStatus);
        return response;
    }

    public static RestResponse createResponse(String responseCode, String responseMessage) {
        RestResponse response = new RestResponse();
        response.getResponseStatus().setResponseCode(responseCode);
        response.getResponseStatus().setResponseMessage(responseMessage);
        return response;
    }

    public RestStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(RestStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}







