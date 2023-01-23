package com.ural.readingisgood.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EnableWebSecurity
public class ResourceServerConfig {


    private static final String ROLES_CLAIM = "roles";
    private static final String SCOPES_CLAIM = "scope";

    @Bean
    public Converter<Jwt, Collection<GrantedAuthority>> jwtToAuthorityConverter() {
        return new Converter<Jwt, Collection<GrantedAuthority>>() {

            @Override
            public Collection<GrantedAuthority> convert(Jwt jwt) {
                List<String> roles = jwt.getClaimAsStringList(ROLES_CLAIM);
                List<String> scope = jwt.getClaimAsStringList(SCOPES_CLAIM);
                if (roles != null) {

                    return Stream.concat(roles.stream().map(eachRole -> new SimpleGrantedAuthority(eachRole)),
                                    scope.stream().map(eachScope -> new SimpleGrantedAuthority(eachScope)))
                            .collect(Collectors.toList());


               /*     return roles.stream().map(eachRole -> new SimpleGrantedAuthority(eachRole)).
                            .collect(Collectors.toList());*/
                }
                return Collections.emptyList();
            }

        };

    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtToAuthorityConverter());

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v0/order/**", "/api/v0/statistics/**")
                .access("  hasAuthority('read') and  hasRole('ADMIN') ")
                .antMatchers(HttpMethod.POST, "/api/v0/book").access("  hasAuthority('read') and  hasRole('ADMIN') ")
                .antMatchers(HttpMethod.PUT, "/api/v0/book/**").access("  hasAuthority('read') and  hasRole('ADMIN') ")
                .antMatchers("/api/v0/**")
                .access("  hasAuthority('read') and  hasRole('CUSTOMER') ")
                .antMatchers(
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/h2-console/**").permitAll()
                //.hasAnyRole("USER")
                //

                .and()
                .oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(jwtAuthenticationConverter);
        return http.build();
    }
}
