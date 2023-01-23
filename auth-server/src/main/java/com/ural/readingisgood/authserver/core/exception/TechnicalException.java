package com.ural.readingisgood.authserver.core.exception;


import com.ural.readingisgood.authserver.core.GenericException;

public class TechnicalException extends GenericException {
    public TechnicalException(ErrorCode code) {
        super(code);
    }
}
