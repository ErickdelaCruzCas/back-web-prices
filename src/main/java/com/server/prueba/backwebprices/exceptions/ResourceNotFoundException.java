package com.server.prueba.backwebprices.exceptions;

import lombok.Generated;

public class ResourceNotFoundException extends RuntimeException {

    private Integer errorCode;

    public ResourceNotFoundException (String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ResourceNotFoundException (String message) {
        super(message);
    }

    @Generated
    public Integer getErrorCode() {
        return this.errorCode;
    }
}
