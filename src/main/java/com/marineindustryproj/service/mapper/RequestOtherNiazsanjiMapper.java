package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.RequestOtherNiazsanjiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RequestOtherNiazsanji and its DTO RequestOtherNiazsanjiDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, RestrictionMapper.class, NiazsanjiInputMapper.class, CourseTypeMapper.class, EducationalModuleMapper.class, PersonMapper.class, OrganizationChartMapper.class, TeachingApproachMapper.class})
public interface RequestOtherNiazsanjiMapper extends EntityMapper<RequestOtherNiazsanjiDTO, RequestOtherNiazsanji> {

    @Mapping(source = "niazsanjiInput.id", target = "niazsanjiInputId")
    @Mapping(source = "niazsanjiInput.title", target = "niazsanjiInputTitle")
    @Mapping(source = "courseType.id", target = "courseTypeId")
    @Mapping(source = "courseType.title", target = "courseTypeTitle")
    @Mapping(source = "educationalModule.id", target = "educationalModuleId")
    @Mapping(source = "educationalModule.title", target = "educationalModuleTitle")
    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.family", target = "personFamily")
    @Mapping(source = "person.name", target = "personName")
    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    @Mapping(source = "teachingApproach.id", target = "teachingApproachId")
    @Mapping(source = "teachingApproach.title", target = "teachingApproachTitle")
    RequestOtherNiazsanjiDTO toDto(RequestOtherNiazsanji requestOtherNiazsanji);

    @Mapping(target = "niazsanjiOthers", ignore = true)
    @Mapping(target = "prioritizeRequestNiazsanjis", ignore = true)
    @Mapping(source = "niazsanjiInputId", target = "niazsanjiInput")
    @Mapping(source = "courseTypeId", target = "courseType")
    @Mapping(source = "educationalModuleId", target = "educationalModule")
    @Mapping(source = "personId", target = "person")
    @Mapping(source = "organizationChartId", target = "organizationChart")
    @Mapping(source = "teachingApproachId", target = "teachingApproach")
    RequestOtherNiazsanji toEntity(RequestOtherNiazsanjiDTO requestOtherNiazsanjiDTO);

    default RequestOtherNiazsanji fromId(Long id) {
        if (id == null) {
            return null;
        }
        RequestOtherNiazsanji requestOtherNiazsanji = new RequestOtherNiazsanji();
        requestOtherNiazsanji.setId(id);
        return requestOtherNiazsanji;
    }
}
