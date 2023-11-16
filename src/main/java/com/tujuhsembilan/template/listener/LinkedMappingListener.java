package com.tujuhsembilan.template.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.tujuhsembilan.template.model.EntityLinkedMapping;
import com.tujuhsembilan.template.model.EntityLinkedMapping.ActionType;
import com.tujuhsembilan.template.model.linkedmapping.LinkedMappingBaseEntity;
import com.tujuhsembilan.template.repository.EntityLinkedMappingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LinkedMappingListener {

    static private EntityLinkedMappingRepository entityLinkedMappingRepository;

    @Autowired
    public void init(EntityLinkedMappingRepository entityLinkedMappingRepository) {
        LinkedMappingListener.entityLinkedMappingRepository = entityLinkedMappingRepository;
    }

    @PrePersist
    public void prePersist(LinkedMappingBaseEntity target) {
        EntityLinkedMapping newEntityLinkedMapping = addNewEvent(ActionType.PERSIST, target);

        target.setLastMapped(newEntityLinkedMapping);
    }

    @PreUpdate
    public void preUpdate(LinkedMappingBaseEntity target) {
        EntityLinkedMapping newEntityLinkedMapping = addNewEvent(ActionType.UPDATE, target);

        target.setLastMapped(newEntityLinkedMapping);
    }

    @PreRemove
    public void preRemove(LinkedMappingBaseEntity target) {
        EntityLinkedMapping newEntityLinkedMapping = addNewEvent(ActionType.UPDATE, target);

        target.setLastMapped(newEntityLinkedMapping);
    }

    private EntityLinkedMapping addNewEvent(ActionType actionType, LinkedMappingBaseEntity obj) {
        EntityLinkedMapping lastEntityLinkedMapping = obj.getLastMapped();

        if (lastEntityLinkedMapping != null && actionType != ActionType.PERSIST) {
            EntityLinkedMapping nextEntityLinkedMapping = EntityLinkedMapping.builder().last(lastEntityLinkedMapping)
                    .action(actionType).build();

            nextEntityLinkedMapping = entityLinkedMappingRepository.save(nextEntityLinkedMapping);
            lastEntityLinkedMapping.setNext(nextEntityLinkedMapping);

            entityLinkedMappingRepository.save(lastEntityLinkedMapping);

            return nextEntityLinkedMapping;
        } else {
            EntityLinkedMapping nextEntityLinkedMapping = EntityLinkedMapping.builder()
                    .action(actionType).build();

            return entityLinkedMappingRepository.save(nextEntityLinkedMapping);
        }
    }
}
