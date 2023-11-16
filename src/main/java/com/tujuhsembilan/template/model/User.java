package com.tujuhsembilan.template.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import com.tujuhsembilan.template.listener.AuditLoggingListener;
import com.tujuhsembilan.template.model.linkedmapping.LinkedMappingBaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * NOTE
 *
 * Generic User Model to be used in the database.
 *
 * Change as needed.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "public")
@EntityListeners({ AuditingEntityListener.class, AuditLoggingListener.class })
public class User extends LinkedMappingBaseEntity implements Serializable {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column
    private String username;

    @Column
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String password;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

}
