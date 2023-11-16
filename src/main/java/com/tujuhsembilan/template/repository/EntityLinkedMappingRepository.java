package com.tujuhsembilan.template.repository;

import com.tujuhsembilan.template.model.EntityLinkedMapping;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityLinkedMappingRepository extends JpaRepository<EntityLinkedMapping, Long> {
    
}
