package com.security.security.controllers;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/demo")
    @PreAuthorize("hasAuthority('admin')")
    public String demo() {
        return "Demo!";
    }

    @GetMapping("/demo2")
    @PreAuthorize("hasAuthority('user')")
    public String demo1() {
        return "Demo2!";
    }

    @GetMapping("/demo3/{username}")
    @PreAuthorize("""
            (#username == authentication.name) or
            (hasAuthority('user'))
            """)
    public String demo3(@PathVariable("username") String username) {
        return "Nombre: " + username;
    }

    @GetMapping("/demo4")
    @PreAuthorize("""
            (@demoCondition.demo4Condition()) or
            (hasAuthority('user'))
            """)
    public String demo4() {
        return "Demo 4";
    }

    @GetMapping("/demo5")
    @PostAuthorize("returnObject != 'Demo 5'") // Se utiliza para evitar el acceso a cierta información
    public String demo5() {
        return "Demo 4";
    }

}
