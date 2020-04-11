package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EducationalCenterServiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EducationalCenterService and its DTO EducationalCenterServiceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EducationalCenterServiceMapper extends EntityMapper<EducationalCenterServiceDTO, EducationalCenterService> {


    @Mapping(target = "educationalCenterGrades", ignore = true)
    EducationalCenterService toEntity(EducationalCenterServiceDTO educationalCenterServiceDTO);

    default EducationalCenterService fromId(Long id) {
        if (id == null) {
            return null;
        }
        EducationalCenterService educationalCenterService = new EducationalCenterService();
        educationalCenterService.setId(id);
        return educationalCenterService;
    }
}
