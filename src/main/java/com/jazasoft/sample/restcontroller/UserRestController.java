package com.jazasoft.sample.restcontroller;

import com.jazasoft.sample.ApiUrls;
import com.jazasoft.sample.assembler.UserAssembler;
import com.jazasoft.sample.dto.UserDto;
import com.jazasoft.sample.service.MyUserDetailsService;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping(ApiUrls.ROOT_URL_USERS)
public class UserRestController{
    
    private final Logger logger = LoggerFactory.getLogger(UserRestController.class);
    
    @Autowired
    MyUserDetailsService userService;  //Service which will do all data retrieval/manipulation work

    @Autowired UserAssembler userAssembler;
    
    @GetMapping
    public ResponseEntity<?> listAllUsers(@RequestParam(value = "after", defaultValue = "0") Long after) {
        logger.debug("listAllUsers()");
        List<UserDto> users = userService.findAllAfter(after);
        Map<String, Object> resp = new HashMap<>();
        resp.put("users", userAssembler.toResources(users));
        resp.put("lastSync", System.currentTimeMillis());
        Resource resource = new Resource(resp, linkTo(UserRestController.class).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
  
    @GetMapping(ApiUrls.URL_USERS_USER)
    public ResponseEntity<?> getUser(@PathVariable("userId") long id) {
        logger.debug("getUser(): id = {}",id);
        UserDto user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userAssembler.toResource(user), HttpStatus.OK);
    }

    @GetMapping(ApiUrls.URL_USERS_USER_SEARCH_BY_NAME)
    public ResponseEntity<?> searchByName(@RequestParam("name") String name){
        logger.debug("searchByName(): name = {}",name);
        UserDto user = userService.findByName(name);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userAssembler.toResource(user), HttpStatus.OK);
    }

    @GetMapping(ApiUrls.URL_USERS_USER_SEARCH_BY_EMAIL)
    public ResponseEntity<?> searchByEmail(@RequestParam("email") String email){
        logger.debug("searchByName(): name = {}",email);
        UserDto user = userService.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userAssembler.toResource(user), HttpStatus.OK);
    }
   
    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserDto user) {
        logger.debug("createUser():\n {}", user.toString());
        user = userService.save(user);
        Link selfLink = linkTo(UserRestController.class).slash(user.getId()).withSelfRel();
        return ResponseEntity.created(URI.create(selfLink.getHref())).build();
    }
 
    @PutMapping(ApiUrls.URL_USERS_USER)
    public ResponseEntity<?> updateUser(@PathVariable("userId") long id,@Validated @RequestBody UserDto user) {
        logger.debug("updateUser(): id = {} \n {}",id,user);
        if (!userService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(id);  
        user = userService.update(user);
        return new ResponseEntity<>(userAssembler.toResource(user), HttpStatus.OK);
    }
  
    @DeleteMapping(ApiUrls.URL_USERS_USER)
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") long id) {
        logger.debug("deleteUser(): id = {}",id);
        if (!userService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
