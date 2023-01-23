package com.ural.readingisgood.authserver.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Role implements GrantedAuthority {

    @Id
    private Long id;
    private String name;


    @Override
    public String getAuthority() {
        return this.name;
    }
}
