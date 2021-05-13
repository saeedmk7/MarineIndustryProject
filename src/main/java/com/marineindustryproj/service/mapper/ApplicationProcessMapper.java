package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.ApplicationProcessDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ApplicationProcess and its DTO ApplicationProcessDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, EducationalRecordMapper.class, PersonMapper.class, OrganizationChartMapper.class, QualificationMapper.class})
public interface ApplicationProcessMapper extends EntityMapper<ApplicationProcessDTO, ApplicationProcess> {

    @Mapping(source = "educationalRecord.id", target = "educationalRecordId")
    @Mapping(source = "educationalRecord.qualificationText", target = "educationalRecordQualificationText")
    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.family", target = "personFamily")
    @Mapping(source = "person.name", target = "personName")
    @Mapping(source = "person.job.title", target = "personJobTitle")
    @Mapping(source = "person.employmentType.title", target = "personEmploymentTypeTitle")
    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    @Mapping(source = "qualification.id", target = "qualificationId")
    @Mapping(source = "qualification.title", target = "qualificationTitle")
    ApplicationProcessDTO toDto(ApplicationProcess applicationProcess);

    @Mapping(target = "applicationProcessRunSteps", ignore = true)
    @Mapping(source = "educationalRecordId", target = "educationalRecord")
    @Mapping(source = "personId", target = "person")
    @Mapping(source = "organizationChartId", target = "organizationChart")
    @Mapping(source = "qualificationId", target = "qualification")
    ApplicationProcess toEntity(ApplicationProcessDTO applicationProcessDTO);

    default ApplicationProcess fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApplicationProcess applicationProcess = new ApplicationProcess();
        applicationProcess.setId(id);
        return applicationProcess;
    }
}
