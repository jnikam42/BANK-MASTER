package com.sarvatra.microservice.exception;


import com.sarvatra.microservice.helper.LogAdaptor;
import com.sarvatra.microservice.helper.LoggerUtil;
import com.sarvatra.microservice.model.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ServiceException extends Exception {

    private static final LogAdaptor LOG = LoggerUtil.getLogger("ServiceException");


    private static final long serialVersionUID = 2936750461229884828L;

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }

    public ServiceException() {
    }


    @ExceptionHandler(Exception.class)
    public RestResponse handleInternalServerError(Exception ex) {
        LOG.error(ex.getMessage(), ex);
        return RestResponse.createResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
}
