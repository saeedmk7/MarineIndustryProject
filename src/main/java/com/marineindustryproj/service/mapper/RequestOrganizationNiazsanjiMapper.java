package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.RequestOrganizationNiazsanjiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RequestOrganizationNiazsanji and its DTO RequestOrganizationNiazsanjiDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class, DocumentMapper.class, OrganizationChartMapper.class, TeacherMapper.class, EducationalModuleMapper.class, TeachApproachMapper.class})
public interface RequestOrganizationNiazsanjiMapper extends EntityMapper<RequestOrganizationNiazsanjiDTO, RequestOrganizationNiazsanji> {

    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "teacher.family", target = "teacherFamily")
    @Mapping(source = "educationalModule.id", target = "educationalModuleId")
    @Mapping(source = "educationalModule.title", target = "educationalModuleTitle")
    @Mapping(source = "teachApproach.id", target = "teachApproachId")
    @Mapping(source = "teachApproach.title", target = "teachApproachTitle")
    RequestOrganizationNiazsanjiDTO toDto(RequestOrganizationNiazsanji requestOrganizationNiazsanji);

    @Mapping(target = "finalOrganizationNiazsanjis", ignore = true)
    @Mapping(source = "organizationChartId", target = "organizationChart")
    @Mapping(source = "teacherId", target = "teacher")
    @Mapping(source = "educationalModuleId", target = "educationalModule")
    @Mapping(source = "teachApproachId", target = "teachApproach")
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
