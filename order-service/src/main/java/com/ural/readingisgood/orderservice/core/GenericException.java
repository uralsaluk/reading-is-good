package com.ural.readingisgood.orderservice.core;


import com.ural.readingisgood.orderservice.exception.ErrorCode;

public class GenericException extends RuntimeException {

    private final transient Object[] params;
    private final String code;
    private final String message;

    private final ErrorType type;

    public GenericException(ErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
        this.params = null;
        this.type = ErrorType.ERROR;
    }


    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ErrorType getType() {
        return type;
    }
}
