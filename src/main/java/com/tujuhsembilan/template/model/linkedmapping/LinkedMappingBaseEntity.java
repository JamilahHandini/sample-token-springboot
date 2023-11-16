package com.tujuhsembilan.template.model.linkedmapping;

import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tujuhsembilan.template.listener.LinkedMappingListener;
import com.tujuhsembilan.template.model.EntityLinkedMapping;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@MappedSuperclass
@EntityListeners(LinkedMappingListener.class)
public class LinkedMappingBaseEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private EntityLinkedMapping lastMapped;
}
