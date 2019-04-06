package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.QualificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Qualification and its DTO QualificationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QualificationMapper extends EntityMapper<QualificationDTO, Qualification> {


    @Mapping(target = "people", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    Qualification toEntity(QualificationDTO qualificationDTO);

    default Qualification fromId(Long id) {
        if (id == null) {
            return null;
        }
        Qualification qualification = new Qualification();
        qualification.setId(id);
        return qualification;
    }
}
