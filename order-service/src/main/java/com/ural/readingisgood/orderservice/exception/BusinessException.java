package com.ural.readingisgood.orderservice.exception;

import com.ural.readingisgood.orderservice.core.GenericException;

public class BusinessException extends GenericException {

    public BusinessException(ErrorCode code) {
        super(code);
    }
}
