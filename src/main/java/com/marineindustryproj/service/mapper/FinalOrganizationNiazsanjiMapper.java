package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.FinalOrganizationNiazsanjiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FinalOrganizationNiazsanji and its DTO FinalOrganizationNiazsanjiDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class, DocumentMapper.class, RestrictionMapper.class, CourseTypeMapper.class, OrganizationChartMapper.class, TeacherMapper.class, EducationalModuleMapper.class, TeachApproachMapper.class, RequestOrganizationNiazsanjiMapper.class, TeachingApproachMapper.class})
public interface FinalOrganizationNiazsanjiMapper extends EntityMapper<FinalOrganizationNiazsanjiDTO, FinalOrganizationNiazsanji> {

    @Mapping(source = "courseType.id", target = "courseTypeId")
    @Mapping(source = "courseType.title", target = "courseTypeTitle")
    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "teacher.family", target = "teacherFamily")
    @Mapping(source = "educationalModule.id", target = "educationalModuleId")
    @Mapping(source = "educationalModule.code", target = "educationalModuleCode")
    @Mapping(source = "educationalModule.title", target = "educationalModuleTitle")
    @Mapping(source = "teachApproach.id", target = "teachApproachId")
    @Mapping(source = "teachApproach.title", target = "teachApproachTitle")
    @Mapping(source = "requestOrganizationNiazsanji.id", target = "requestOrganizationNiazsanjiId")
    @Mapping(source = "requestOrganizationNiazsanji.recommendedByOrgchart", target = "requestOrganizationNiazsanjiRecommendedByOrgchart")
    @Mapping(source = "teachingApproach.id", target = "teachingApproachId")
    @Mapping(source = "teachingApproach.title", target = "teachingApproachTitle")
    FinalOrganizationNiazsanjiDTO toDto(FinalOrganizationNiazsanji finalOrganizationNiazsanji);

    @Mapping(source = "courseTypeId", target = "courseType")
    @Mapping(source = "organizationChartId", target = "organizationChart")
    @Mapping(source = "teacherId", target = "teacher")
    @Mapping(source = "educationalModuleId", target = "educationalModule")
    @Mapping(source = "teachApproachId", target = "teachApproach")
    @Mapping(source = "requestOrganizationNiazsanjiId", target = "requestOrganizationNiazsanji")
    @Mapping(source = "teachingApproachId", target = "teachingApproach")
    FinalOrganizationNiazsanji toEntity(FinalOrganizationNiazsanjiDTO finalOrganizationNiazsanjiDTO);

    default FinalOrganizationNiazsanji fromId(Long id) {
        if (id == null) {
            return null;
        }
        FinalOrganizationNiazsanji finalOrganizationNiazsanji = new FinalOrganizationNiazsanji();
        finalOrganizationNiazsanji.setId(id);
        return finalOrganizationNiazsanji;
    }
}
