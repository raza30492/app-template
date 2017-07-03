package com.jazasoft.sample.service;

import com.jazasoft.sample.dto.UserDto;
import com.jazasoft.sample.entity.User;
import com.jazasoft.sample.respository.UserRepository;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by mdzahidraza on 26/06/17.
 */
@Service
@Transactional(readOnly = true)
public class MyUserDetailsService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.trace("Looking for user for {}", username);
        try {
            Optional<User> user = userRepository.findOneByUsername(username);
            if (!user.isPresent()) {
                user = userRepository.findOneByEmail(username);
                if (!user.isPresent()) {
                    LOGGER.info("USER NOT PRESENT for {}", username);
                    throw new UsernameNotFoundException("user not found");
                }
            }
            LOGGER.trace("Found user for {}", username);
            return user.get();
        } catch (Exception e) {
            LOGGER.error("Error loading user {}", username, e);
        }
        return null;
    }


    @Autowired
    Mapper mapper;

    public User findOne(Long id) {
        LOGGER.debug("findOne(): id = {}",id);
        return userRepository.findOne(id);
    }

    public List<User> findAll() {
        LOGGER.debug("findAll()");
        return userRepository.findAll();
    }

    public List<User> findAllAfter(long after) {
        LOGGER.debug("findAllAfter(): after = {}" , after);
        return userRepository.findByModifiedAtGreaterThan(new Date(after));
    }

    public User findByEmail(String email) {
        LOGGER.debug("findByEmail(): email = {}",email);
        return userRepository.findByEmail(email);
    }

    public User findByName(String name) {
        LOGGER.debug("findByName(): name = " , name);
        return userRepository.findByName(name);
    }

    public User findByUsername(String username) {
        LOGGER.debug("findByUsername(): username = " , username);
        return userRepository.findByUsername(username);
    }

    public Boolean exists(Long id) {
        LOGGER.debug("exists(): id = ",id);
        return userRepository.exists(id);
    }

    public Long count(){
        LOGGER.debug("count()");
        return userRepository.count();
    }

    @Transactional
    public User save(User user) {
        LOGGER.debug("save()");
        user.setPassword(user.getMobile());
        user.setEnabled(true);
        return userRepository.save(user);
    }

    @Transactional
    public User update(UserDto userDto) {
        LOGGER.debug("update()");
        User user = userRepository.findOne(userDto.getId());
        System.out.println("UserDto = " + userDto);
        mapper.map(userDto,user);
        System.out.println("User = " + user);
        return user;
    }

    @Transactional
    public void delete(Long id) {
        LOGGER.debug("delete(): id = {}",id);
        User user = userRepository.findOne(id);
        user.setEnabled(false);
    }
}
