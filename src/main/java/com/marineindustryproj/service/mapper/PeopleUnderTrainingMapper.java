package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.PeopleUnderTrainingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PeopleUnderTraining and its DTO PeopleUnderTrainingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PeopleUnderTrainingMapper extends EntityMapper<PeopleUnderTrainingDTO, PeopleUnderTraining> {


    @Mapping(target = "educationalModules", ignore = true)
    @Mapping(target = "requestEducationalModules", ignore = true)
    PeopleUnderTraining toEntity(PeopleUnderTrainingDTO peopleUnderTrainingDTO);

    default PeopleUnderTraining fromId(Long id) {
        if (id == null) {
            return null;
        }
        PeopleUnderTraining peopleUnderTraining = new PeopleUnderTraining();
        peopleUnderTraining.setId(id);
        return peopleUnderTraining;
    }
}
