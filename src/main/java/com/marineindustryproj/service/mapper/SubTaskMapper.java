package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.SubTaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SubTask and its DTO SubTaskDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SubTaskMapper extends EntityMapper<SubTaskDTO, SubTask> {


    @Mapping(target = "mainTasks", ignore = true)
    SubTask toEntity(SubTaskDTO subTaskDTO);

    default SubTask fromId(Long id) {
        if (id == null) {
            return null;
        }
        SubTask subTask = new SubTask();
        subTask.setId(id);
        return subTask;
    }
}
