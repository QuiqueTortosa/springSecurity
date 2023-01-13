package com.security.security.config.providers;

import com.security.security.config.authentication.ApiKeyAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@AllArgsConstructor
public class ApiKeyProvider implements AuthenticationProvider {

    @Value("{the.secret}")
    private final String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthentication auth = (ApiKeyAuthentication) authentication;
        if(key.equals(auth.getKey())){
            auth.setAuthenticated(true);
            return auth;
        }
        throw new BadCredentialsException("Wrong credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //Comprueba que soporta este tipo de autentificacion
        return ApiKeyAuthentication.class.equals(authentication);
    }
}