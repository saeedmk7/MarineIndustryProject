package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.ConditionsOfParticipantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ConditionsOfParticipant and its DTO ConditionsOfParticipantDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConditionsOfParticipantMapper extends EntityMapper<ConditionsOfParticipantDTO, ConditionsOfParticipant> {


    @Mapping(target = "designAndPlannings", ignore = true)
    ConditionsOfParticipant toEntity(ConditionsOfParticipantDTO conditionsOfParticipantDTO);

    default ConditionsOfParticipant fromId(Long id) {
        if (id == null) {
            return null;
        }
        ConditionsOfParticipant conditionsOfParticipant = new ConditionsOfParticipant();
        conditionsOfParticipant.setId(id);
        return conditionsOfParticipant;
    }
}
