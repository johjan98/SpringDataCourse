package com.course.springdata.persistence.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * This class helps add the necessary columns for auditing.
 * Add @EntityListeners(AuditingEntityListener.class) in the entities and extends this class
 */
@MappedSuperclass
public class Auditable {
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
}
