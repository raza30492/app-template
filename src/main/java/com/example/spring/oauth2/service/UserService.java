/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.spring.oauth2.service;

import com.example.spring.oauth2.entity.User;
import com.example.spring.oauth2.respository.UserRespository;
import java.util.List;
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

    @Autowired
    UserRespository userRespository;

    public User findOne(Long id) {
        return userRespository.findOne(id);
    }

    public List<User> findAll() {
        return userRespository.findAll();
    }

    public User findByEmail(String email) {
        return userRespository.findByEmail(email);
    }

    public Boolean exists(Long id) {
        return userRespository.exists(id);
    }

    @Transactional
    public User save(User user) {
        return userRespository.save(user);
    }

    @Transactional
    public User update(User user) {
        User user2 = userRespository.findOne(user.getId());
        user2.setEmail(user.getEmail());
        user2.setMobile(user.getMobile());
        user2.setName(user.getName());
        user2.setRole(user.getRole());
        return user2;
    }
    
    @Transactional
    public void delete(Long id) {
        userRespository.delete(id);
    }
    
}
