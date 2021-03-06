package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.DocumentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Document and its DTO DocumentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DocumentMapper extends EntityMapper<DocumentDTO, Document> {


    @Mapping(target = "people", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    @Mapping(target = "teacherGrades", ignore = true)
    @Mapping(target = "jobs", ignore = true)
    @Mapping(target = "educationalModules", ignore = true)
    @Mapping(target = "requestEducationalModules", ignore = true)
    @Mapping(target = "educationalCenters", ignore = true)
    @Mapping(target = "educationalCenterGrades", ignore = true)
    @Mapping(target = "resources", ignore = true)
    @Mapping(target = "requestOrganizationNiazsanjis", ignore = true)
    @Mapping(target = "finalOrganizationNiazsanjis", ignore = true)
    @Mapping(target = "finalNiazsanjiReports", ignore = true)
    @Mapping(target = "finalNiazsanjiReportPeople", ignore = true)
    @Mapping(target = "niazsanjiPersonGrades", ignore = true)
    @Mapping(target = "levelThreeEffectivenesses", ignore = true)
    @Mapping(target = "levelFourEffectivenesses", ignore = true)
    @Mapping(target = "designAndPlannings", ignore = true)
    @Mapping(target = "runPhases", ignore = true)
    @Mapping(target = "announcements", ignore = true)
    @Mapping(target = "usersRequests", ignore = true)
    @Mapping(target = "niazsanjiFardis", ignore = true)
    @Mapping(target = "requestNiazsanjiFardis", ignore = true)
    @Mapping(target = "instructions", ignore = true)
    @Mapping(target = "investToGroupTransactions", ignore = true)
    @Mapping(target = "mediaAwarenessReports", ignore = true)
    @Mapping(target = "preJobNiazsanjis", ignore = true)
    @Mapping(target = "jobNiazsanjis", ignore = true)
    @Mapping(target = "niazsanjiOthers", ignore = true)
    @Mapping(target = "requestOtherNiazsanjis", ignore = true)
    @Mapping(target = "prioritizeRequestNiazsanjis", ignore = true)
    @Mapping(target = "soldiers", ignore = true)
    @Mapping(target = "soldierTrainingReports", ignore = true)
    @Mapping(target = "soldierMediaAwarenessReports", ignore = true)
    @Mapping(target = "evaluateCriteriaTrainings", ignore = true)
    @Mapping(target = "evaluateCriteriaData", ignore = true)
    @Mapping(target = "effectivenessPhases", ignore = true)
    @Mapping(target = "monitorLearningProcesses", ignore = true)
    @Mapping(target = "matchingEducationalRecords", ignore = true)
    @Mapping(target = "applicationProcesses", ignore = true)
    Document toEntity(DocumentDTO documentDTO);

    default Document fromId(Long id) {
        if (id == null) {
            return null;
        }
        Document document = new Document();
        document.setId(id);
        return document;
    }
}
