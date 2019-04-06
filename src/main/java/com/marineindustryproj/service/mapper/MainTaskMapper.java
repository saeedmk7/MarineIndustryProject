package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.MainTaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MainTask and its DTO MainTaskDTO.
 */
@Mapper(componentModel = "spring", uses = {SubTaskMapper.class, JobMapper.class, PersonMapper.class})
public interface MainTaskMapper extends EntityMapper<MainTaskDTO, MainTask> {



    default MainTask fromId(Long id) {
        if (id == null) {
            return null;
        }
        MainTask mainTask = new MainTask();
        mainTask.setId(id);
        return mainTask;
    }
}
