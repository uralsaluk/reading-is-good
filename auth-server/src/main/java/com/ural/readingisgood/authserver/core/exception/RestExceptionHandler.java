package com.ural.readingisgood.authserver.core.exception;

import com.ural.readingisgood.authserver.core.BaseResponse;
import com.ural.readingisgood.authserver.core.ErrorType;
import com.ural.readingisgood.authserver.core.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String TECHNICAL_MESSAGE = "There is a technical error";

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse> handleBusinessException(BusinessException ex, HttpServletRequest request) {

        BaseResponse response = new BaseResponse();
        ResultResponse result = new ResultResponse(ex.getMessage(), ex.getCode(), ErrorType.ERROR.getErrorTypeString(), getRootCause(ex), ex);
        response.setResult(result);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleException(Exception ex, HttpServletRequest request) {

        BaseResponse response = new BaseResponse();
        ResultResponse result = new ResultResponse(ex.getMessage(), "500", ErrorType.ERROR.getErrorTypeString(), getRootCause(ex), ex);
        response.setResult(result);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private String getRootCause(Throwable t) {

        if (t == null) {

            return "";
        } else {

            Throwable rootCause = ExceptionUtils.getRootCause(t);
            return rootCause == null ? "" : rootCause.getClass().getName();
        }
    }

}
