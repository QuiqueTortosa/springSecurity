package com.security.security.conditions;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DemoCondition {

    public boolean demo4Condition(){
        return SecurityContextHolder.getContext().getAuthentication().getName().equals("admin");
    }

}
