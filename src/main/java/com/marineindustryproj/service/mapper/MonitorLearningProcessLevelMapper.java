package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.MonitorLearningProcessLevelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MonitorLearningProcessLevel and its DTO MonitorLearningProcessLevelDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MonitorLearningProcessLevelMapper extends EntityMapper<MonitorLearningProcessLevelDTO, MonitorLearningProcessLevel> {


    @Mapping(target = "monitorLearningProcessGrades", ignore = true)
    MonitorLearningProcessLevel toEntity(MonitorLearningProcessLevelDTO monitorLearningProcessLevelDTO);

    default MonitorLearningProcessLevel fromId(Long id) {
        if (id == null) {
            return null;
        }
        MonitorLearningProcessLevel monitorLearningProcessLevel = new MonitorLearningProcessLevel();
        monitorLearningProcessLevel.setId(id);
        return monitorLearningProcessLevel;
    }
}
