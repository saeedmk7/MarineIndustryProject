package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.MonitorProcessDurationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MonitorProcessDuration and its DTO MonitorProcessDurationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MonitorProcessDurationMapper extends EntityMapper<MonitorProcessDurationDTO, MonitorProcessDuration> {


    @Mapping(target = "monitorLearningProcesses", ignore = true)
    @Mapping(target = "monitorLearningProcessGrades", ignore = true)
    MonitorProcessDuration toEntity(MonitorProcessDurationDTO monitorProcessDurationDTO);

    default MonitorProcessDuration fromId(Long id) {
        if (id == null) {
            return null;
        }
        MonitorProcessDuration monitorProcessDuration = new MonitorProcessDuration();
        monitorProcessDuration.setId(id);
        return monitorProcessDuration;
    }
}
