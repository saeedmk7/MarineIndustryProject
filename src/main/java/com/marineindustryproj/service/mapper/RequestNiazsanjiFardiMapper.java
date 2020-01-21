package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.RequestNiazsanjiFardiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RequestNiazsanjiFardi and its DTO RequestNiazsanjiFardiDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, CourseTypeMapper.class, EducationalModuleMapper.class, PersonMapper.class, OrganizationChartMapper.class})
public interface RequestNiazsanjiFardiMapper extends EntityMapper<RequestNiazsanjiFardiDTO, RequestNiazsanjiFardi> {

    @Mapping(source = "courseType.id", target = "courseTypeId")
    @Mapping(source = "courseType.title", target = "courseTypeTitle")
    @Mapping(source = "approvedEducationalModule.id", target = "approvedEducationalModuleId")
    @Mapping(source = "approvedEducationalModule.title", target = "approvedEducationalModuleTitle")
    @Mapping(source = "allEducationalModule.id", target = "allEducationalModuleId")
    @Mapping(source = "allEducationalModule.title", target = "allEducationalModuleTitle")
    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.name", target = "personName")
    @Mapping(source = "person.family", target = "personFamily")
    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    RequestNiazsanjiFardiDTO toDto(RequestNiazsanjiFardi requestNiazsanjiFardi);

    @Mapping(target = "niazsanjiFardis", ignore = true)
    @Mapping(target = "prioritizeRequestNiazsanjis", ignore = true)
    @Mapping(source = "courseTypeId", target = "courseType")
    @Mapping(source = "approvedEducationalModuleId", target = "approvedEducationalModule")
    @Mapping(source = "allEducationalModuleId", target = "allEducationalModule")
    @Mapping(source = "personId", target = "person")
    @Mapping(source = "organizationChartId", target = "organizationChart")
    RequestNiazsanjiFardi toEntity(RequestNiazsanjiFardiDTO requestNiazsanjiFardiDTO);

    default RequestNiazsanjiFardi fromId(Long id) {
        if (id == null) {
            return null;
        }
        RequestNiazsanjiFardi requestNiazsanjiFardi = new RequestNiazsanjiFardi();
        requestNiazsanjiFardi.setId(id);
        return requestNiazsanjiFardi;
    }
}
