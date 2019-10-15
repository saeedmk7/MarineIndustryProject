package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.ForceRunningPercentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ForceRunningPercent and its DTO ForceRunningPercentDTO.
 */
@Mapper(componentModel = "spring", uses = {OrganizationChartMapper.class})
public interface ForceRunningPercentMapper extends EntityMapper<ForceRunningPercentDTO, ForceRunningPercent> {



    default ForceRunningPercent fromId(Long id) {
        if (id == null) {
            return null;
        }
        ForceRunningPercent forceRunningPercent = new ForceRunningPercent();
        forceRunningPercent.setId(id);
        return forceRunningPercent;
    }
}
