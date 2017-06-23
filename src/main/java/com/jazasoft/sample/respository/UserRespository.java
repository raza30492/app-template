package com.jazasoft.sample.respository;

import com.jazasoft.sample.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Long>{
    
    public User findByEmail(String email);
    
    public User findByName(String name);
}
