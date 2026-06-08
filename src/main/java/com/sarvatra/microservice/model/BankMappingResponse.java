package com.sarvatra.microservice.model;


import java.util.List;

public class BankMappingResponse extends RestResponse {

    private List<BankServiceMapDTO> bankMappingServices;

    public BankMappingResponse(List<BankServiceMapDTO> bankMappingServices) {this.bankMappingServices= bankMappingServices;}

    public BankMappingResponse() {}

    public List<BankServiceMapDTO> getBankMappingServices() {return bankMappingServices;}

    public void setBankMappingServices(List<BankServiceMapDTO> bankMappingService) {this.bankMappingServices = bankMappingService;}
}

