package com.ural.readingisgood.orderservice.exception;


import com.ural.readingisgood.orderservice.core.GenericException;

public class TechnicalException extends GenericException {
    public TechnicalException(ErrorCode code) {
        super(code);
    }
}
