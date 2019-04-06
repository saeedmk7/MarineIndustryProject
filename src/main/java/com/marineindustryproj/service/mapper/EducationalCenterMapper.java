package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EducationalCenterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EducationalCenter and its DTO EducationalCenterDTO.
 */
@Mapper(componentModel = "spring", uses = {ActivityAreaMapper.class, DocumentMapper.class})
public interface EducationalCenterMapper extends EntityMapper<EducationalCenterDTO, EducationalCenter> {


    @Mapping(target = "educationalModules", ignore = true)
    @Mapping(target = "requestEducationalModules", ignore = true)
    EducationalCenter toEntity(EducationalCenterDTO educationalCenterDTO);

    default EducationalCenter fromId(Long id) {
        if (id == null) {
            return null;
        }
        EducationalCenter educationalCenter = new EducationalCenter();
        educationalCenter.setId(id);
        return educationalCenter;
    }
}
