package com.jazasoft.sample;

import com.jazasoft.sample.dto.UserDto;
import com.jazasoft.sample.entity.User;
import com.jazasoft.sample.service.MyUserDetailsService;
import java.util.ArrayList;
import java.util.List;

import com.jazasoft.sample.util.Utils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Application/* extends SpringBootServletInitializer*/{
    
    private final Logger logger = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Application.class);
//    }

    @Bean
    CommandLineRunner init(MyUserDetailsService userService) {

        return (args) -> {
            if(userService.count() == 0){
                User user = new User("Md Zahid Raza","zahid7292","zahid7292@gmail.com","admin","8987525008",true,false,false,false);
                user.setRoles(Utils.getRoles("ROLE_ADMIN"));
                userService.save(user);
                //userService.save(new UserDto("Md Zahid Raza","zahid7292","zahid7292@gmail.com","ROLE_ADMIN","8987525008"));
            }           
        };
    }
    
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
    
     @Bean
    public Mapper dozerBeanMapper() {
        List<String> list = new ArrayList<>();
        list.add("dozer_mapping.xml");
        return new DozerBeanMapper(list);
    }
    
    @GetMapping(value= "/")
    @ResponseBody
    public ResponseEntity<?> hello() {
        logger.debug("home page");
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }
}
