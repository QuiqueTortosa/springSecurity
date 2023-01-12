package com.security.security.services;

import com.security.security.repositories.UserRepository;
import com.security.security.security.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Con esto conseguimos también que este en springContext
@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    //Este metodo inyecta los usuarios al security context
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = userRepository.findUserByUsername(username);
        return u.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found" + username));
    }
}
