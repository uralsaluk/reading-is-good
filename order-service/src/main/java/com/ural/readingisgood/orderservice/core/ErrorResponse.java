package com.ural.readingisgood.orderservice.core;

import java.io.Serializable;

public interface ErrorResponse  extends Serializable {

    String getCode();

    String getMessage();

    String getType();

    ResultResponseDetail getDetail();

}
