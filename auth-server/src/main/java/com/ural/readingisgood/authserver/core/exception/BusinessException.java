package com.ural.readingisgood.authserver.core.exception;

import com.ural.readingisgood.authserver.core.GenericException;

public class BusinessException extends GenericException {

    public BusinessException(ErrorCode code) {
        super(code);
    }
}
