package com.ural.readingisgood.authserver.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TokenResponse {

    private String access_token;
    private String refresh_token;
    private String scope;
    private String id_token;


}
