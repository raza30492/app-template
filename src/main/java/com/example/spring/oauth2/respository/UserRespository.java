/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.spring.oauth2.respository;

import com.example.spring.oauth2.entity.User;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author razamd
 */
public interface UserRespository extends JpaRepository<User, Long>{
    
    public User findByEmail(String email);
}
