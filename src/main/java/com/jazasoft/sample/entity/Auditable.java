package com.jazasoft.sample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mdzahidraza on 26/06/17.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @JsonIgnore
    @CreatedDate
    protected Date createdAt;

    @JsonIgnore
    @LastModifiedDate
    protected Date modifiedAt;

    @JsonIgnore
    @CreatedBy
    protected U createdBy;

    @JsonIgnore
    @LastModifiedBy
    protected U modifiedBy;

    protected Boolean enabled;

    public Auditable() {
    }

    public Auditable(Long id) {
        this.id = id;
    }

    public Auditable(Boolean enabled) {
        this.enabled = enabled;
    }

    public Auditable(Long id, Boolean enabled) {
        this.id = id;
        this.enabled = enabled;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public U getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    public U getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(U modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
