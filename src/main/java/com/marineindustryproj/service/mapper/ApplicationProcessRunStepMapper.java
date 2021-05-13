package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.ApplicationProcessRunStepDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ApplicationProcessRunStep and its DTO ApplicationProcessRunStepDTO.
 */
@Mapper(componentModel = "spring", uses = {ApplicationProcessMapper.class, ApplicationProcessStepMapper.class})
public interface ApplicationProcessRunStepMapper extends EntityMapper<ApplicationProcessRunStepDTO, ApplicationProcessRunStep> {

    @Mapping(source = "applicationProcess.id", target = "applicationProcessId")
    @Mapping(source = "applicationProcess.code", target = "applicationProcessCode")
    @Mapping(source = "applicationProcessStep.id", target = "applicationProcessStepId")
    @Mapping(source = "applicationProcessStep.title", target = "applicationProcessStepTitle")
    ApplicationProcessRunStepDTO toDto(ApplicationProcessRunStep applicationProcessRunStep);

    @Mapping(source = "applicationProcessId", target = "applicationProcess")
    @Mapping(source = "applicationProcessStepId", target = "applicationProcessStep")
    ApplicationProcessRunStep toEntity(ApplicationProcessRunStepDTO applicationProcessRunStepDTO);

    default ApplicationProcessRunStep fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApplicationProcessRunStep applicationProcessRunStep = new ApplicationProcessRunStep();
        applicationProcessRunStep.setId(id);
        return applicationProcessRunStep;
    }
}
