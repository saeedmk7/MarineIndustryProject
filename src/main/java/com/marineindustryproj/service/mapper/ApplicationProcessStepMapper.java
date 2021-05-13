package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.ApplicationProcessStepDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ApplicationProcessStep and its DTO ApplicationProcessStepDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ApplicationProcessStepMapper extends EntityMapper<ApplicationProcessStepDTO, ApplicationProcessStep> {


    @Mapping(target = "applicationProcessRunSteps", ignore = true)
    ApplicationProcessStep toEntity(ApplicationProcessStepDTO applicationProcessStepDTO);

    default ApplicationProcessStep fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApplicationProcessStep applicationProcessStep = new ApplicationProcessStep();
        applicationProcessStep.setId(id);
        return applicationProcessStep;
    }
}
