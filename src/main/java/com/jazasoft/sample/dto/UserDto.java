package com.jazasoft.sample.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jazasoft.sample.Role;
import com.jazasoft.sample.validation.StringEnum;
import org.hibernate.validator.constraints.Email;
import org.springframework.hateoas.core.Relation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Relation(collectionRelation = "users",value = "user")
public class UserDto {

    private Long id;

    @NotNull
    @Size(min = 5, max = 100)
    private String name;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_.-]{5,30}$")
    private String username;

    @NotNull
    @Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    private String email;

    @JsonIgnore
    private String password;

    @NotNull
    @StringEnum(enumClass = Role.class)
    private String roles;

    @NotNull
    @Pattern(regexp="[0-9]{10}")
    private String mobile;

    private Boolean active;

    public UserDto() {
    }

    public UserDto(String name, String username, String email, String roles, String mobile) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roles='" + roles + '\'' +
                ", mobile='" + mobile + '\'' +
                ", active=" + active +
                '}';
    }
}
