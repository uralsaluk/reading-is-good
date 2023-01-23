package com.ural.readingisgood.orderservice.util;

import java.util.List;


public class ContextUser implements CustomContext {

    private String email;
    private String firstName;
    private String lastName;

    private List<String> roles;
    private List<String> scopes;


    public ContextUser(String email, String firstName, String lastName, List<String> roles, List<String> scopes) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.scopes = scopes;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getName() {
        return this.firstName;
    }

    @Override
    public String getSurname() {
        return this.lastName;
    }

    @Override
    public List<String> getRoles() {
        return this.roles;
    }

    @Override
    public List<String> getScopes() {
        return this.scopes;
    }


    @Override
    public String toString() {
        return "ContextUser{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roles=" + roles +
                ", scopes=" + scopes +
                '}';
    }
}
