package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.FinalOrganizationNiazsanjiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FinalOrganizationNiazsanji and its DTO FinalOrganizationNiazsanjiDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class, DocumentMapper.class, OrganizationChartMapper.class, TeacherMapper.class, EducationalModuleMapper.class, TeachApproachMapper.class, RequestOrganizationNiazsanjiMapper.class})
public interface FinalOrganizationNiazsanjiMapper extends EntityMapper<FinalOrganizationNiazsanjiDTO, FinalOrganizationNiazsanji> {

    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "teacher.family", target = "teacherFamily")
    @Mapping(source = "educationalModule.id", target = "educationalModuleId")
    @Mapping(source = "educationalModule.title", target = "educationalModuleTitle")
    @Mapping(source = "teachApproach.id", target = "teachApproachId")
    @Mapping(source = "teachApproach.title", target = "teachApproachTitle")
    @Mapping(source = "requestOrganizationNiazsanji.id", target = "requestOrganizationNiazsanjiId")
    @Mapping(source = "requestOrganizationNiazsanji.recommendedByOrgchart", target = "requestOrganizationNiazsanjiRecommendedByOrgchart")
    FinalOrganizationNiazsanjiDTO toDto(FinalOrganizationNiazsanji finalOrganizationNiazsanji);

    @Mapping(source = "organizationChartId", target = "organizationChart")
    @Mapping(source = "teacherId", target = "teacher")
    @Mapping(source = "educationalModuleId", target = "educationalModule")
    @Mapping(source = "teachApproachId", target = "teachApproach")
    @Mapping(source = "requestOrganizationNiazsanjiId", target = "requestOrganizationNiazsanji")
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
