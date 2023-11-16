package com.tujuhsembilan.template.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "entity_mappings")
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class EntityLinkedMapping {

    public enum ActionType {
        PERSIST, UPDATE, DELETE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @CreatedDate
    @Column
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime timestamp;

    @CreatedBy
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private User actor;

    @Column
    @Enumerated(EnumType.STRING)
    private ActionType action;

    @OneToOne(optional = true, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private EntityLinkedMapping last;

    @OneToOne(optional = true, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private EntityLinkedMapping next;

}
