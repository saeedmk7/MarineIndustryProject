package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.RequestOrganizationNiazsanjiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RequestOrganizationNiazsanji and its DTO RequestOrganizationNiazsanjiDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class, DocumentMapper.class, RestrictionMapper.class, CourseTypeMapper.class, OrganizationChartMapper.class, TeacherMapper.class, EducationalModuleMapper.class, TeachApproachMapper.class, TeachingApproachMapper.class})
public interface RequestOrganizationNiazsanjiMapper extends EntityMapper<RequestOrganizationNiazsanjiDTO, RequestOrganizationNiazsanji> {

    @Mapping(source = "courseType.id", target = "courseTypeId")
    @Mapping(source = "courseType.title", target = "courseTypeTitle")
    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "teacher.family", target = "teacherFamily")
    @Mapping(source = "educationalModule.id", target = "educationalModuleId")
    @Mapping(source = "educationalModule.code", target = "educationalModuleCode")
    @Mapping(source = "educationalModule.title", target = "educationalModuleTitle")
    @Mapping(source = "educationalModule.skillableLevelOfSkill.title", target = "skillLevelOfSkillTitle")
    @Mapping(source = "educationalModule.learningTimeTheorical", target = "learningTimeTheorical")
    @Mapping(source = "educationalModule.learningTimePractical", target = "learningTimePractical")
    @Mapping(source = "teachApproach.id", target = "teachApproachId")
    @Mapping(source = "teachApproach.title", target = "teachApproachTitle")
    @Mapping(source = "teachingApproach.id", target = "teachingApproachId")
    @Mapping(source = "teachingApproach.title", target = "teachingApproachTitle")
    RequestOrganizationNiazsanjiDTO toDto(RequestOrganizationNiazsanji requestOrganizationNiazsanji);

    @Mapping(target = "finalOrganizationNiazsanjis", ignore = true)
    @Mapping(source = "courseTypeId", target = "courseType")
    @Mapping(source = "organizationChartId", target = "organizationChart")
    @Mapping(source = "teacherId", target = "teacher")
    @Mapping(source = "educationalModuleId", target = "educationalModule")
    @Mapping(source = "teachApproachId", target = "teachApproach")
    @Mapping(source = "teachingApproachId", target = "teachingApproach")
    RequestOrganizationNiazsanji toEntity(RequestOrganizationNiazsanjiDTO requestOrganizationNiazsanjiDTO);

    default RequestOrganizationNiazsanji fromId(Long id) {
        if (id == null) {
            return null;
        }
        RequestOrganizationNiazsanji requestOrganizationNiazsanji = new RequestOrganizationNiazsanji();
        requestOrganizationNiazsanji.setId(id);
        return requestOrganizationNiazsanji;
    }
}
