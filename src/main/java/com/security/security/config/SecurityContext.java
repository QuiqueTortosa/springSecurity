package com.security.security.config;

import com.security.security.config.filters.ApiKeyFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityContext {

    @Value("${the.secret}")
    private String key;

    //HttpSecurity es un Bean que se añade al SpringSecurityContext permitiendo accede a el en cualquier lugar de la app
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.httpBasic()
                .and()
                //addFilterAt: Se suma al filtro que le indiquemos, tiene que pasar correctamenet los dos filtros
                //addFilterBefore/After: Con que pase el primer filtro se autentifica correctamente
                .addFilterAt(new ApiKeyFilter(key), BasicAuthenticationFilter.class)
                .authorizeRequests().anyRequest().authenticated()   // authorization
                //.and().authenticationManager()   or  by adding a bean of type AuthenticationManager
                //.and().authenticationProvider() it doesn't override the AP, it adds one more to the collection
                .and().build();
    }

        @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // should not use this in a prod app => BCryptPasswordEncoder
    }
}
