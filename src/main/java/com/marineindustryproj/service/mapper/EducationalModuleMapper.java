package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EducationalModuleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EducationalModule and its DTO EducationalModuleDTO.
 */
@Mapper(componentModel = "spring", uses = {ScientificWorkGroupMapper.class, DocumentMapper.class, EducationalCenterMapper.class, GoalMapper.class, ResourceMapper.class, TeacherMapper.class, RequestEducationalModuleMapper.class, SecurityLevelMapper.class, SkillableLevelOfSkillMapper.class, EvaluationMethodMapper.class, OrganizationMapper.class})
public interface EducationalModuleMapper extends EntityMapper<EducationalModuleDTO, EducationalModule> {

    @Mapping(source = "requestEducationalModule.id", target = "requestEducationalModuleId")
    @Mapping(source = "requestEducationalModule.title", target = "requestEducationalModuleTitle")
    @Mapping(source = "securityLevel.id", target = "securityLevelId")
    @Mapping(source = "securityLevel.title", target = "securityLevelTitle")
    @Mapping(source = "skillableLevelOfSkill.id", target = "skillableLevelOfSkillId")
    @Mapping(source = "skillableLevelOfSkill.title", target = "skillableLevelOfSkillTitle")
    @Mapping(source = "evaluationMethod.id", target = "evaluationMethodId")
    @Mapping(source = "evaluationMethod.title", target = "evaluationMethodTitle")
    @Mapping(source = "organization.id", target = "organizationId")
    @Mapping(source = "organization.title", target = "organizationTitle")
    EducationalModuleDTO toDto(EducationalModule educationalModule);

    @Mapping(target = "educationalModuleJobs", ignore = true)
    @Mapping(target = "requestOrganizationNiazsanjis", ignore = true)
    @Mapping(target = "finalOrganizationNiazsanjis", ignore = true)
    @Mapping(target = "finalNiazsanjiReports", ignore = true)
    @Mapping(target = "niazsanjiFardis", ignore = true)
    @Mapping(target = "approvedRequestNiazsanjiFardis", ignore = true)
    @Mapping(target = "allRequestNiazsanjiFardis", ignore = true)
    @Mapping(source = "requestEducationalModuleId", target = "requestEducationalModule")
    @Mapping(source = "securityLevelId", target = "securityLevel")
    @Mapping(source = "skillableLevelOfSkillId", target = "skillableLevelOfSkill")
    @Mapping(source = "evaluationMethodId", target = "evaluationMethod")
    @Mapping(source = "organizationId", target = "organization")
    @Mapping(target = "niazsanjiGroups", ignore = true)
    EducationalModule toEntity(EducationalModuleDTO educationalModuleDTO);

    default EducationalModule fromId(Long id) {
        if (id == null) {
            return null;
        }
        EducationalModule educationalModule = new EducationalModule();
        educationalModule.setId(id);
        return educationalModule;
    }
}
