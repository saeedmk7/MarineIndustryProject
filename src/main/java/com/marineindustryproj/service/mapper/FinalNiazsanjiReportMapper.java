package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FinalNiazsanjiReport and its DTO FinalNiazsanjiReportDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, RestrictionMapper.class, NiazsanjiIntegrationMapper.class, TeacherMapper.class, NiazsanjiInputMapper.class, CourseTypeMapper.class, OrganizationChartMapper.class, EducationalModuleMapper.class, TeachingApproachMapper.class})
public interface FinalNiazsanjiReportMapper extends EntityMapper<FinalNiazsanjiReportDTO, FinalNiazsanjiReport> {

    @Mapping(source = "niazsanjiIntegration.id", target = "niazsanjiIntegrationId")
    @Mapping(source = "niazsanjiIntegration.niazsanjiYear", target = "niazsanjiIntegrationNiazsanjiYear")
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "teacher.family", target = "teacherFamily")
    @Mapping(source = "niazsanjiInput.id", target = "niazsanjiInputId")
    @Mapping(source = "niazsanjiInput.title", target = "niazsanjiInputTitle")
    @Mapping(source = "courseType.id", target = "courseTypeId")
    @Mapping(source = "courseType.title", target = "courseTypeTitle")
    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    @Mapping(source = "educationalModule.id", target = "educationalModuleId")
    @Mapping(source = "educationalModule.code", target = "educationalModuleCode")
    @Mapping(source = "educationalModule.title", target = "educationalModuleTitle")
    @Mapping(source = "teachingApproach.id", target = "teachingApproachId")
    @Mapping(source = "teachingApproach.title", target = "teachingApproachTitle")
    FinalNiazsanjiReportDTO toDto(FinalNiazsanjiReport finalNiazsanjiReport);

    @Mapping(target = "finalNiazsanjiReportPeople", ignore = true)
    @Mapping(target = "designAndPlannings", ignore = true)
    @Mapping(target = "runPhases", ignore = true)
    @Mapping(target = "polls", ignore = true)
    @Mapping(source = "niazsanjiIntegrationId", target = "niazsanjiIntegration")
    @Mapping(source = "teacherId", target = "teacher")
    @Mapping(source = "niazsanjiInputId", target = "niazsanjiInput")
    @Mapping(source = "courseTypeId", target = "courseType")
    @Mapping(source = "organizationChartId", target = "organizationChart")
    @Mapping(source = "educationalModuleId", target = "educationalModule")
    @Mapping(source = "teachingApproachId", target = "teachingApproach")
    FinalNiazsanjiReport toEntity(FinalNiazsanjiReportDTO finalNiazsanjiReportDTO);

    default FinalNiazsanjiReport fromId(Long id) {
        if (id == null) {
            return null;
        }
        FinalNiazsanjiReport finalNiazsanjiReport = new FinalNiazsanjiReport();
        finalNiazsanjiReport.setId(id);
        return finalNiazsanjiReport;
    }
}
