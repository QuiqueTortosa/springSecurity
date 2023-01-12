package com.security.security.security;

import com.security.security.entities.Rol;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityRol implements GrantedAuthority {

    private final Rol rol;

    @Override
    public String getAuthority() {
        return rol.getName();
    }
}
