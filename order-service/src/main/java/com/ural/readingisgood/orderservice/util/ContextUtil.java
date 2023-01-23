package com.ural.readingisgood.orderservice.util;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

public class ContextUtil {

    public static String getUserId() {

        return SecurityContextHolder.getContext().getAuthentication().getName();

    }


    public static SecurityContext getSecurityContext() {

        return SecurityContextHolder.getContext();

    }

    public static SecurityContextHolderStrategy getContextStrategy() {

        return SecurityContextHolder.getContextHolderStrategy();

    }


    public static CustomContext getContextUser() {
        Jwt token = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> claims = token.getClaims();

        CustomContext context = new ContextUser(
                (String) claims.get("sub"),
                (String) claims.get("firstName"),
                (String) claims.get("lastName"),
                token.getClaimAsStringList("roles"),
                token.getClaimAsStringList("scope"));


        return context;

    }

}
