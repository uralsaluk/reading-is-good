package com.ural.readingisgood.orderservice.core;

import java.io.Serializable;

public class ResultResponseDetail implements Serializable {

    private String rootCause;

    private String errorOrigin;

    public ResultResponseDetail() {
        this.rootCause=null;
        this.errorOrigin=null;
    }

    public ResultResponseDetail(String rootCause, String errorOrigin) {
        this.rootCause = rootCause;
        this.errorOrigin = errorOrigin;
    }

    public String getRootCause() {
        return rootCause;
    }

    public String getErrorOrigin() {
        return errorOrigin;
    }
}
