package com.server.prueba.backwebprices.api.error;

import com.baeldung.openapi.model.ErrorResource;
import com.server.prueba.backwebprices.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ResourceNotFoundExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleConflict(ResourceNotFoundException exception, WebRequest request) {
        var error = new ErrorResource();
        error.setCode(exception.getErrorCode());
        error.setMessage(exception.getMessage());
        return error.toString();
    }
}
