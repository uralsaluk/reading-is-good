package com.ural.readingisgood.authserver.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class BaseResponse implements Serializable {

    @Valid
    @NotNull
    @JsonProperty("result")
    private ResultResponse result;

    public BaseResponse() {
        this.result=new ResultResponse();
    }

    public ResultResponse getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(ResultResponse result) {
        this.result = result;
    }
}
