package com.example.spring.oauth2.service;

import com.example.spring.oauth2.dto.UserDto;
import com.example.spring.oauth2.entity.User;
import com.example.spring.oauth2.page.converter.UserConverter;
import com.example.spring.oauth2.respository.UserRespository;
import java.util.List;
import java.util.stream.Collectors;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    UserRespository userRepository;
    
    @Autowired Mapper mapper;
    
    @Autowired UserConverter converter;

    public UserDto findOne(Long id) {
        return mapper.map(userRepository.findOne(id), UserDto.class);
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
    
    public Page<UserDto> findAllByPage(Pageable pageable){
        return userRepository.findAll(pageable).map(converter);
    }

    public UserDto findByEmail(String email) {
        return mapper.map(userRepository.findByEmail(email), UserDto.class);
    }
    
    public UserDto findByUsername(String username) {
        return mapper.map(userRepository.findByName(username), UserDto.class);
    }

    public Boolean exists(Long id) {
        return userRepository.exists(id);
    }
    
    public Long count(){
        return userRepository.count();
    }

    @Transactional
    public UserDto save(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setPassword(userDto.getMobile());
        user = userRepository.save(user);
        return mapper.map(user, UserDto.class);
    }

    @Transactional
    public UserDto update(UserDto userDto) {
        User user = userRepository.findOne(userDto.getId());
        user = mapper.map(userDto, User.class);
        return mapper.map(user, UserDto.class);
    }
    
    @Transactional
    public void delete(Long id) {
        userRepository.delete(id);
    }
    
}
