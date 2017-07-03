package com.jazasoft.sample.assembler;

import com.jazasoft.sample.entity.User;
import com.jazasoft.sample.restcontroller.UserRestController;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler extends ResourceAssemblerSupport<User, Resource>{

    public UserAssembler(){
        super(UserRestController.class, Resource.class);
    }

    @Override
    public Resource toResource(User userDto) {
        return new Resource<>(userDto, linkTo(methodOn(UserRestController.class).getUser(userDto.getId())).withSelfRel());
    }

    @Override
    public List<Resource> toResources(Iterable<? extends User> users) {
        List<Resource> resources = new ArrayList<>();
        for(User user : users) {
            resources.add(toResource(user));
        }
        return resources;
    }

}
