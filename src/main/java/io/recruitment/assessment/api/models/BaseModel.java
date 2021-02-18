package io.recruitment.assessment.api.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Nullable
    private Long id;

    @Nullable
    public Long getId() {
        return this.id;
    }

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @Nullable
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Nullable
    private Date updatedAt;

    @Nullable
    public Date getCreatedAt() {
        return createdAt;
    }

    @Nullable
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    protected void prePersist() {
        if (this.createdAt == null) createdAt = new Date();
        if (this.updatedAt == null) updatedAt = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new Date();
    }

    @PreRemove
    protected void preRemove() {
        this.updatedAt = new Date();
    }
}
