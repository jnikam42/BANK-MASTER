package com.sarvatra.microservice.helper;

public class LoggerUtil extends LogAdaptor {

    private LoggerUtil(String className) {
        super(className);
    }

    public static LogAdaptor getLogger(String className) {
        return new LoggerUtil(className);
    }
}
