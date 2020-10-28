package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.MonitorLearningProcessDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MonitorLearningProcess and its DTO MonitorLearningProcessDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, MonitorProcessDurationMapper.class})
public interface MonitorLearningProcessMapper extends EntityMapper<MonitorLearningProcessDTO, MonitorLearningProcess> {

    @Mapping(source = "monitorProcessDuration.id", target = "monitorProcessDurationId")
    @Mapping(source = "monitorProcessDuration.title", target = "monitorProcessDurationTitle")
    MonitorLearningProcessDTO toDto(MonitorLearningProcess monitorLearningProcess);

    @Mapping(source = "monitorLearningProcessGrades", target = "monitorLearningProcessGrades")
    @Mapping(source = "monitorProcessDurationId", target = "monitorProcessDuration")
    MonitorLearningProcess toEntity(MonitorLearningProcessDTO monitorLearningProcessDTO);

    default MonitorLearningProcess fromId(Long id) {
        if (id == null) {
            return null;
        }
        MonitorLearningProcess monitorLearningProcess = new MonitorLearningProcess();
        monitorLearningProcess.setId(id);
        return monitorLearningProcess;
    }
}
