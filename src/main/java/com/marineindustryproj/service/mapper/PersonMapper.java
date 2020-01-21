package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.PersonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Person and its DTO PersonDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, ScientificWorkGroupMapper.class, QualificationMapper.class, FieldOfStudyMapper.class, EmploymentTypeMapper.class, WorkGroupMapper.class, WorkIndustryMapper.class, JobMapper.class, OrganizationChartMapper.class})
public interface PersonMapper extends EntityMapper<PersonDTO, Person> {

    @Mapping(source = "lastQualification.id", target = "lastQualificationId")
    @Mapping(source = "lastQualification.title", target = "lastQualificationTitle")
    @Mapping(source = "lastFieldOfStudy.id", target = "lastFieldOfStudyId")
    @Mapping(source = "lastFieldOfStudy.title", target = "lastFieldOfStudyTitle")
    @Mapping(source = "employmentType.id", target = "employmentTypeId")
    @Mapping(source = "employmentType.title", target = "employmentTypeTitle")
    @Mapping(source = "workGroup.id", target = "workGroupId")
    @Mapping(source = "workGroup.title", target = "workGroupTitle")
    @Mapping(source = "workIndustry.id", target = "workIndustryId")
    @Mapping(source = "workIndustry.title", target = "workIndustryTitle")
    @Mapping(source = "job.id", target = "jobId")
    @Mapping(source = "job.title", target = "jobTitle")
    @Mapping(source = "job.jobCode", target = "jobCode")
    @Mapping(source = "practicaljob.id", target = "practicaljobId")
    @Mapping(source = "practicaljob.title", target = "practicaljobTitle")
    @Mapping(source = "practicaljob.jobCode", target = "practicaljobCode")
    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    PersonDTO toDto(Person person);

    @Mapping(target = "finalNiazsanjiReportPeople", ignore = true)
    @Mapping(target = "pollScores", ignore = true)
    @Mapping(target = "niazsanjiFardis", ignore = true)
    @Mapping(target = "requestNiazsanjiFardis", ignore = true)
    @Mapping(target = "educationalHistories", ignore = true)
    @Mapping(target = "educationalRecords", ignore = true)
    @Mapping(target = "jobRecords", ignore = true)
    @Mapping(target = "researchRecords", ignore = true)
    @Mapping(target = "teachingRecords", ignore = true)
    @Mapping(target = "preJobNiazsanjis", ignore = true)
    @Mapping(target = "jobNiazsanjis", ignore = true)
    @Mapping(target = "niazsanjiOthers", ignore = true)
    @Mapping(target = "requestOtherNiazsanjis", ignore = true)
    @Mapping(target = "prioritizeRequestNiazsanjis", ignore = true)
    @Mapping(source = "lastQualificationId", target = "lastQualification")
    @Mapping(source = "lastFieldOfStudyId", target = "lastFieldOfStudy")
    @Mapping(source = "employmentTypeId", target = "employmentType")
    @Mapping(source = "workGroupId", target = "workGroup")
    @Mapping(source = "workIndustryId", target = "workIndustry")
    @Mapping(source = "jobId", target = "job")
    @Mapping(source = "practicaljobId", target = "practicaljob")
    @Mapping(source = "organizationChartId", target = "organizationChart")
    @Mapping(target = "mainTasks", ignore = true)
    @Mapping(target = "requestOrganizationNiazsanjis", ignore = true)
    @Mapping(target = "finalOrganizationNiazsanjis", ignore = true)
    @Mapping(target = "designAndPlannings", ignore = true)
    @Mapping(target = "runPhases", ignore = true)
    Person toEntity(PersonDTO personDTO);

    default Person fromId(Long id) {
        if (id == null) {
            return null;
        }
        Person person = new Person();
        person.setId(id);
        return person;
    }
}
