package com.jazasoft.sample.respository;

import com.jazasoft.sample.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserRespository extends JpaRepository<User, Long>{
    
    User findByEmail(String email);
    
    User findByName(String name);

    User findByUsername(String username);

    List<User> findByLastModifiedGreaterThan(Date lastModified);
}
