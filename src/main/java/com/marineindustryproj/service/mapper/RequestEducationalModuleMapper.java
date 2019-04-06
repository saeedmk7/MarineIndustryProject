package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.RequestEducationalModuleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RequestEducationalModule and its DTO RequestEducationalModuleDTO.
 */
@Mapper(componentModel = "spring", uses = {ScientificWorkGroupMapper.class, DocumentMapper.class, EducationalCenterMapper.class, GoalMapper.class, ResourceMapper.class, TeacherMapper.class, SecurityLevelMapper.class, SkillableLevelOfSkillMapper.class, EvaluationMethodMapper.class, OrganizationMapper.class})
public interface RequestEducationalModuleMapper extends EntityMapper<RequestEducationalModuleDTO, RequestEducationalModule> {

    @Mapping(source = "securityLevel.id", target = "securityLevelId")
    @Mapping(source = "securityLevel.title", target = "securityLevelTitle")
    @Mapping(source = "skillableLevelOfSkill.id", target = "skillableLevelOfSkillId")
    @Mapping(source = "skillableLevelOfSkill.title", target = "skillableLevelOfSkillTitle")
    @Mapping(source = "evaluationMethod.id", target = "evaluationMethodId")
    @Mapping(source = "evaluationMethod.title", target = "evaluationMethodTitle")
    @Mapping(source = "organization.id", target = "organizationId")
    @Mapping(source = "organization.title", target = "organizationTitle")
    RequestEducationalModuleDTO toDto(RequestEducationalModule requestEducationalModule);

    @Mapping(target = "educationalModules", ignore = true)
    @Mapping(source = "securityLevelId", target = "securityLevel")
    @Mapping(source = "skillableLevelOfSkillId", target = "skillableLevelOfSkill")
    @Mapping(source = "evaluationMethodId", target = "evaluationMethod")
    @Mapping(source = "organizationId", target = "organization")
    RequestEducationalModule toEntity(RequestEducationalModuleDTO requestEducationalModuleDTO);

    default RequestEducationalModule fromId(Long id) {
        if (id == null) {
            return null;
        }
        RequestEducationalModule requestEducationalModule = new RequestEducationalModule();
        requestEducationalModule.setId(id);
        return requestEducationalModule;
    }
}
