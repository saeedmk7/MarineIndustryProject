package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.MonitorLearningProcessGradeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MonitorLearningProcessGrade and its DTO MonitorLearningProcessGradeDTO.
 */
@Mapper(componentModel = "spring", uses = {MonitorLearningProcessLevelMapper.class, MonitorLearningProcessMapper.class, MonitorProcessDurationMapper.class})
public interface MonitorLearningProcessGradeMapper extends EntityMapper<MonitorLearningProcessGradeDTO, MonitorLearningProcessGrade> {

    @Mapping(source = "monitorLearningProcessLevel.id", target = "monitorLearningProcessLevelId")
    @Mapping(source = "monitorLearningProcessLevel.title", target = "monitorLearningProcessLevelTitle")
    @Mapping(source = "monitorLearningProcess.id", target = "monitorLearningProcessId")
    @Mapping(source = "monitorLearningProcess.title", target = "monitorLearningProcessTitle")
    @Mapping(source = "monitorProcessDuration.id", target = "monitorProcessDurationId")
    @Mapping(source = "monitorProcessDuration.title", target = "monitorProcessDurationTitle")
    MonitorLearningProcessGradeDTO toDto(MonitorLearningProcessGrade monitorLearningProcessGrade);

    @Mapping(source = "monitorLearningProcessLevelId", target = "monitorLearningProcessLevel")
    @Mapping(source = "monitorLearningProcessId", target = "monitorLearningProcess")
    @Mapping(source = "monitorProcessDurationId", target = "monitorProcessDuration")
    MonitorLearningProcessGrade toEntity(MonitorLearningProcessGradeDTO monitorLearningProcessGradeDTO);

    default MonitorLearningProcessGrade fromId(Long id) {
        if (id == null) {
            return null;
        }
        MonitorLearningProcessGrade monitorLearningProcessGrade = new MonitorLearningProcessGrade();
        monitorLearningProcessGrade.setId(id);
        return monitorLearningProcessGrade;
    }
}
