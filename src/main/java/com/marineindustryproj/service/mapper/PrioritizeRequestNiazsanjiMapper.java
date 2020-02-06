package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.PrioritizeRequestNiazsanjiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PrioritizeRequestNiazsanji and its DTO PrioritizeRequestNiazsanjiDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, RestrictionMapper.class, RequestNiazsanjiFardiMapper.class, PreJobNiazsanjiMapper.class, RequestOtherNiazsanjiMapper.class, NiazsanjiInputMapper.class, CourseTypeMapper.class, EducationalModuleMapper.class, PersonMapper.class, OrganizationChartMapper.class, TeachingApproachMapper.class})
public interface PrioritizeRequestNiazsanjiMapper extends EntityMapper<PrioritizeRequestNiazsanjiDTO, PrioritizeRequestNiazsanji> {

    @Mapping(source = "requestNiazsanjiFardi.id", target = "requestNiazsanjiFardiId")
    @Mapping(source = "requestNiazsanjiFardi.code", target = "requestNiazsanjiFardiCode")
    @Mapping(source = "preJobNiazsanji.id", target = "preJobNiazsanjiId")
    @Mapping(source = "preJobNiazsanji.title", target = "preJobNiazsanjiTitle")
    @Mapping(source = "requestOtherNiazsanji.id", target = "requestOtherNiazsanjiId")
    @Mapping(source = "requestOtherNiazsanji.code", target = "requestOtherNiazsanjiCode")
    @Mapping(source = "niazsanjiInput.id", target = "niazsanjiInputId")
    @Mapping(source = "niazsanjiInput.title", target = "niazsanjiInputTitle")
    @Mapping(source = "courseType.id", target = "courseTypeId")
    @Mapping(source = "courseType.title", target = "courseTypeTitle")
    @Mapping(source = "educationalModule.id", target = "educationalModuleId")
    @Mapping(source = "educationalModule.code", target = "educationalModuleCode")
    @Mapping(source = "educationalModule.title", target = "educationalModuleTitle")
    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.family", target = "personFamily")
    @Mapping(source = "person.name", target = "personName")
    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    @Mapping(source = "teachingApproach.id", target = "teachingApproachId")
    @Mapping(source = "teachingApproach.title", target = "teachingApproachTitle")
    PrioritizeRequestNiazsanjiDTO toDto(PrioritizeRequestNiazsanji prioritizeRequestNiazsanji);

    @Mapping(target = "niazsanjiIntegrations", ignore = true)
    @Mapping(source = "requestNiazsanjiFardiId", target = "requestNiazsanjiFardi")
    @Mapping(source = "preJobNiazsanjiId", target = "preJobNiazsanji")
    @Mapping(source = "requestOtherNiazsanjiId", target = "requestOtherNiazsanji")
    @Mapping(source = "niazsanjiInputId", target = "niazsanjiInput")
    @Mapping(source = "courseTypeId", target = "courseType")
    @Mapping(source = "educationalModuleId", target = "educationalModule")
    @Mapping(source = "personId", target = "person")
    @Mapping(source = "organizationChartId", target = "organizationChart")
    @Mapping(source = "teachingApproachId", target = "teachingApproach")
    PrioritizeRequestNiazsanji toEntity(PrioritizeRequestNiazsanjiDTO prioritizeRequestNiazsanjiDTO);

    default PrioritizeRequestNiazsanji fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrioritizeRequestNiazsanji prioritizeRequestNiazsanji = new PrioritizeRequestNiazsanji();
        prioritizeRequestNiazsanji.setId(id);
        return prioritizeRequestNiazsanji;
    }
}
