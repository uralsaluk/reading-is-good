package com.ural.readingisgood.authserver.core;

import java.io.Serializable;

public interface ErrorResponse  extends Serializable {

    String getCode();

    String getMessage();

    String getType();

    ResultResponseDetail getDetail();

}
