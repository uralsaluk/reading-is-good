package com.ural.readingisgood.authserver.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class ContextUser extends User {

    private String firstName;
    private String lastName;


    public ContextUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public ContextUser(String username,
                       String password,
                       boolean enabled,
                       boolean accountNonExpired,
                       boolean credentialsNonExpired,
                       boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
                       String firstName, String lastName) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
