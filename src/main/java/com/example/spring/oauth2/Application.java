/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.spring.oauth2;

import com.example.spring.oauth2.entity.Role;
import com.example.spring.oauth2.entity.User;
import com.example.spring.oauth2.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author razamd
 */
@SpringBootApplication
@Controller
public class Application extends SpringBootServletInitializer{
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    
    
    
    @Bean
    CommandLineRunner init(
            UserService userService) {

        return (args) -> {
            userService.save(new User(55555L,"Md Zahid Raza","zahid7292@gmail.com","admin",Role.ADMIN.getValue(),"8987525008"));
            userService.save(new User(55550L,"Md Taufeeque Alam","taufeeque8@gmail.com","moonlight",Role.USER.getValue(),"8987525008"));
        };
    }
    
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
    
    @GetMapping(value= "/")
    @ResponseBody
    public ResponseEntity<?> hello() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }
}
