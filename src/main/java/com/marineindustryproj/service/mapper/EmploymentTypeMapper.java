package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EmploymentTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EmploymentType and its DTO EmploymentTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmploymentTypeMapper extends EntityMapper<EmploymentTypeDTO, EmploymentType> {


    @Mapping(target = "people", ignore = true)
    EmploymentType toEntity(EmploymentTypeDTO employmentTypeDTO);

    default EmploymentType fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmploymentType employmentType = new EmploymentType();
        employmentType.setId(id);
        return employmentType;
    }
}
