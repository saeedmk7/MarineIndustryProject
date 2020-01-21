package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.NiazsanjiOtherDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NiazsanjiOther and its DTO NiazsanjiOtherDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, RestrictionMapper.class, NiazsanjiInputMapper.class, CourseTypeMapper.class, RequestOtherNiazsanjiMapper.class, EducationalModuleMapper.class, PersonMapper.class, OrganizationChartMapper.class, TeachingApproachMapper.class})
public interface NiazsanjiOtherMapper extends EntityMapper<NiazsanjiOtherDTO, NiazsanjiOther> {

    @Mapping(source = "niazsanjiInput.id", target = "niazsanjiInputId")
    @Mapping(source = "niazsanjiInput.title", target = "niazsanjiInputTitle")
    @Mapping(source = "courseType.id", target = "courseTypeId")
    @Mapping(source = "courseType.title", target = "courseTypeTitle")
    @Mapping(source = "requestOtherNiazsanji.id", target = "requestOtherNiazsanjiId")
    @Mapping(source = "requestOtherNiazsanji.code", target = "requestOtherNiazsanjiCode")
    @Mapping(source = "educationalModule.id", target = "educationalModuleId")
    @Mapping(source = "educationalModule.title", target = "educationalModuleTitle")
    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.family", target = "personFamily")
    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    @Mapping(source = "teachingApproach.id", target = "teachingApproachId")
    @Mapping(source = "teachingApproach.title", target = "teachingApproachTitle")
    NiazsanjiOtherDTO toDto(NiazsanjiOther niazsanjiOther);

    @Mapping(source = "niazsanjiInputId", target = "niazsanjiInput")
    @Mapping(source = "courseTypeId", target = "courseType")
    @Mapping(source = "requestOtherNiazsanjiId", target = "requestOtherNiazsanji")
    @Mapping(source = "educationalModuleId", target = "educationalModule")
    @Mapping(source = "personId", target = "person")
    @Mapping(source = "organizationChartId", target = "organizationChart")
    @Mapping(source = "teachingApproachId", target = "teachingApproach")
    NiazsanjiOther toEntity(NiazsanjiOtherDTO niazsanjiOtherDTO);

    default NiazsanjiOther fromId(Long id) {
        if (id == null) {
            return null;
        }
        NiazsanjiOther niazsanjiOther = new NiazsanjiOther();
        niazsanjiOther.setId(id);
        return niazsanjiOther;
    }
}
