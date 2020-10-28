package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EducationalCenterGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EducationalCenterGroup and its DTO EducationalCenterGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EducationalCenterGroupMapper extends EntityMapper<EducationalCenterGroupDTO, EducationalCenterGroup> {


    @Mapping(target = "educationalCenterGrades", ignore = true)
    @Mapping(target = "educationalCenterCriteria", ignore = true)
    EducationalCenterGroup toEntity(EducationalCenterGroupDTO educationalCenterGroupDTO);

    default EducationalCenterGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        EducationalCenterGroup educationalCenterGroup = new EducationalCenterGroup();
        educationalCenterGroup.setId(id);
        return educationalCenterGroup;
    }
}
