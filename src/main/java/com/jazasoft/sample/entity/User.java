package com.jazasoft.sample.entity;

import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jazasoft.sample.Role;
import com.jazasoft.sample.util.Utils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "users", indexes = @Index(columnList = "name,email,username"))
public class User extends BaseEntity implements UserDetails{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "mobile")
    private String mobile;

    @JsonIgnore
    @Column(name = "retry_count")
    private Integer retryCount;

    @JsonIgnore
    @Column(name = "otp")
    private String otp;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "otp_sent_at")
    private Date otpSentAt;

    @Column(name = "account_expired")
    private boolean accountExpired;

    @Column(name = "account_locked")
    private boolean accountLocked;

    @Column(name = "credential_expired")
    private boolean credentialsExpired;

    @Column(name = "roles")
    private String roles;

    public User() {
    }

    public User(String name, String username, String email, String password, String mobile,boolean enabled, boolean accountExpired, boolean accountLocked, boolean credentialsExpired) {
        super(enabled);
        this.name = name;
        this.username = username;
        this.email = email;
        setPassword(password);
        this.mobile = mobile;
        this.accountExpired = accountExpired;
        this.accountLocked = accountLocked;
        this.credentialsExpired = credentialsExpired;
    }

    public User(String name, String username, String email, String password, String mobile) {
        this.name = name;
        this.username = username;
        this.email = email;
        setPassword(password);
        this.mobile = mobile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roleList = getRoles();
        List<GrantedAuthority> rls = new ArrayList<>();
        for (Role role: roleList) {
            rls.add(new SimpleGrantedAuthority(role.getValue()));
        }
        return rls;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getOtpSentAt() {
        return otpSentAt;
    }

    public void setOtpSentAt(Date otpSentAt) {
        this.otpSentAt = otpSentAt;
    }

    public void setRoles(Set<Role> roles) {
        if (roles != null) {
            StringBuilder builder = new StringBuilder();
            for (Role role: roles) {
                builder.append(role.getValue()).append(",");
            }
            if (builder.length() > 0) {
                builder.setLength(builder.length()-1);
                this.roles = builder.toString();
            }
        }
    }

    public Set<Role> getRoles() {
        return Utils.getRoles(this.roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", updatedAt=" + updatedAt +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
