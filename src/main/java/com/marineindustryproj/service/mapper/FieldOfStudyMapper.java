package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.FieldOfStudyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FieldOfStudy and its DTO FieldOfStudyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FieldOfStudyMapper extends EntityMapper<FieldOfStudyDTO, FieldOfStudy> {


    @Mapping(target = "people", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    FieldOfStudy toEntity(FieldOfStudyDTO fieldOfStudyDTO);

    default FieldOfStudy fromId(Long id) {
        if (id == null) {
            return null;
        }
        FieldOfStudy fieldOfStudy = new FieldOfStudy();
        fieldOfStudy.setId(id);
        return fieldOfStudy;
    }
}
