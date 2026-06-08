package com.sarvatra.microservice.exception;

import com.sarvatra.microservice.helper.LogAdaptor;
import com.sarvatra.microservice.helper.LoggerUtil;
import com.sarvatra.microservice.model.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    private static final LogAdaptor LOG = LoggerUtil.getLogger("ApplicationExceptionHandler");

    /**
     * Handles exceptions of type {@link MethodArgumentNotValidException} that occur
     * when method arguments fail validation.
     *
     * @param ex the exception thrown when method arguments are not valid. It
     *           contains details about the validation errors.
     * @return a {@link ResponseEntity} containing a {@link RestResponse} with a
     * status code of 400 (Bad Request) and a message detailing the
     * validation errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ObjectError error = ex.getBindingResult().getAllErrors().get(0);

        String rawMessage = error.getDefaultMessage();
        String responseCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
        String responseMessage = rawMessage;

        if (rawMessage != null && rawMessage.contains("::")) {
            String[] parts = rawMessage.split("::", 2);
            if (parts.length == 2) {
                responseCode = parts[0].trim();
                responseMessage = parts[1].trim();
            }
        }

        RestResponse response = RestResponse.setResponse(responseCode, responseMessage);

        return ResponseEntity.ok().body(response);
    }

}