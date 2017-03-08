/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.spring.oauth2.restcontroller;

import com.example.spring.oauth2.entity.User;
import com.example.spring.oauth2.service.UserService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author razamd
 */
@RestController
//@RequestMapping("/users")
public class UserRestController {
    
    @Autowired UserService userService;  //Service which will do all data retrieval/manipulation work
  
      
    //-------------------Retrieve All Users--------------------------------------------------------
      
    @GetMapping(value= "/users")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
  
  
    //-------------------Retrieve Single User--------------------------------------------------------
      
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.findOne(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
  
      
      
    //-------------------Create a User--------------------------------------------------------
      
    @PostMapping(value = "/users")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        System.out.println("Creating User " + user.getName());
  
        if (userService.exists(user.getId())) {
            System.out.println("A User with Id " + user.getId()+ " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
  
        user = userService.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(linkTo(UserRestController.class).slash(user.getId()).withSelfRel().getHref()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
  
      
    //------------------- Update a User --------------------------------------------------------
      
    @PutMapping(value = "/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);
          
        if (!userService.exists(id)) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
  
        user.setId(id);
          
        user = userService.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
  
    //------------------- Delete a User --------------------------------------------------------
      
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
  
        //User user = userService.findOne(id);
        if (!userService.exists(id)) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
  
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  

}
