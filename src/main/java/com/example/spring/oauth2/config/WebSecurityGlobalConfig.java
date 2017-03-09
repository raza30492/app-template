/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.spring.oauth2.config;

import com.example.spring.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author razamd
 */
@Configuration
public class WebSecurityGlobalConfig extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        return (email) -> {
            com.example.spring.oauth2.entity.User user = userService.findByEmail(email);
            if (user != null) {
                return new User(user.getName(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().getValue()));
            } else {
                throw new UsernameNotFoundException("Could not find the user '" + email + "'");
            }
        };
    }
}
