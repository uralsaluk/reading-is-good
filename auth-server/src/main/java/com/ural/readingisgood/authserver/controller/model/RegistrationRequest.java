package com.ural.readingisgood.authserver.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationRequest {

    private String email;
    private transient String password;
    private String firstname;
    private String lastname;

}
