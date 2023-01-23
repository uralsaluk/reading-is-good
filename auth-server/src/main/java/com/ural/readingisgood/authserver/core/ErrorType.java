package com.ural.readingisgood.authserver.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

    ERROR("ERROR"),
    INFO("INFO");

    private final String errorTypeString;
}
