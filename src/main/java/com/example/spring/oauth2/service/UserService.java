/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.spring.oauth2.service;

import com.example.spring.oauth2.entity.User;
import com.example.spring.oauth2.respository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author razamd
 */
@Service
@Transactional(readOnly = true)
public class UserService {
    
    @Autowired UserRespository userRespository;
    
    public User findByEmail(String email){
        return userRespository.findByEmail(email);
    }
    
     @Transactional
    public User save(User user) {
        //logger.info("save(): " + user);
        return userRespository.save(user);
    }
}
