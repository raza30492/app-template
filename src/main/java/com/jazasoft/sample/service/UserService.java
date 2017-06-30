package com.jazasoft.sample.service;

import com.jazasoft.sample.dto.UserDto;
import com.jazasoft.sample.entity.User;
import com.jazasoft.sample.respository.UserRespository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
    
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRespository userRepository;
    
    @Autowired Mapper mapper;

    public UserDto findOne(Long id) {
        logger.debug("findOne(): id = {}",id);
        User user = userRepository.findOne(id);
        if (user != null) {
            return mapper.map(userRepository.findOne(id), UserDto.class);
        }
        return null;
    }

    public List<UserDto> findAll() {
        logger.debug("findAll()");
        return userRepository.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public List<UserDto> findAllAfter(long after) {
        logger.debug("findAllAfter(): after = {}" , after);
        return userRepository.findByLastModifiedGreaterThan(new Date(after)).stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto findByEmail(String email) {
        logger.debug("findByEmail(): email = {}",email);
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return mapper.map(user, UserDto.class);
        }
        return null;
    }
    
    public UserDto findByName(String name) {
        logger.debug("findByName(): name = " , name);
        User user = userRepository.findByName(name);
        if (user != null) {
            return mapper.map(user, UserDto.class);
        }
        return null;
    }

    public UserDto findByUsername(String username) {
        logger.debug("findByUsername(): username = " , username);
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return mapper.map(user, UserDto.class);
        }
        return null;
    }

    public Boolean exists(Long id) {
        logger.debug("exists(): id = ",id);
        return userRepository.exists(id);
    }
    
    public Long count(){
        logger.debug("count()");
        return userRepository.count();
    }

    @Transactional
    public UserDto save(UserDto userDto) {
        logger.debug("save()");
        User user = mapper.map(userDto, User.class);
        user.setPassword(userDto.getMobile());
        user.setActive(true);
        user = userRepository.save(user);
        return mapper.map(user, UserDto.class);
    }

    @Transactional
    public UserDto update(UserDto userDto) {
        logger.debug("update()");
        User user = userRepository.findOne(userDto.getId());
        user = mapper.map(userDto, User.class);
        return mapper.map(user, UserDto.class);
    }
    
    @Transactional
    public void delete(Long id) {
        logger.debug("delete(): id = {}",id);
        userRepository.delete(id);
    }
    
}
