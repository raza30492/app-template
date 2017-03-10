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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    UserRespository userRepository;

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    public Page<User> findAllByPage(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Boolean exists(Long id) {
        return userRepository.exists(id);
    }

    @Transactional
    public User save(User user) {
        user.setPassword(user.getMobile());
        return userRepository.save(user);
    }

    @Transactional
    public User update(User user) {
        User user2 = userRepository.findOne(user.getId());
        user2.setEmail(user.getEmail());
        user2.setMobile(user.getMobile());
        user2.setName(user.getName());
        user2.setRole(user.getRole());
        return user2;
    }
    
    @Transactional
    public void delete(Long id) {
        userRepository.delete(id);
    }
    
}
