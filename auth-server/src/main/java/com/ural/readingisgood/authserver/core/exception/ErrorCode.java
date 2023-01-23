package com.ural.readingisgood.authserver.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    MAIL_ALREADY_EXIST("M001", "Mail is already exist");


    private final String code;
    private final String message;
}
