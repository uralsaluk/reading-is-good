package com.ural.readingisgood.authserver.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ural.readingisgood.authserver.core.exception.TechnicalException;

import java.io.Serializable;

public class ResultResponse implements Serializable, ErrorResponse {

    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private String code;

    @JsonProperty("type")
    private String type;


    private ResultResponseDetail detail;

    public ResultResponse() {
        this.code = "1";
        this.message = "Success";
        this.type = "INFO";
        this.detail = new ResultResponseDetail();
    }

    public ResultResponse(String message, String code, String type, String rootCause, Throwable t) {
        this.message = message;
        this.code = code;
        this.type = type;
        String errorOrigin = "";
        if (t instanceof TechnicalException) {

            errorOrigin = t.getStackTrace()[0].toString();
        } else {

            errorOrigin = "BUSINESS";
        }


        this.detail = new ResultResponseDetail(rootCause, errorOrigin);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getType() {
        return type;
    }


    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public ResultResponseDetail getDetail() {
        return detail;
    }

    @JsonProperty("detail")
    public void setDetail(ResultResponseDetail detail) {
        this.detail = detail;
    }
}
