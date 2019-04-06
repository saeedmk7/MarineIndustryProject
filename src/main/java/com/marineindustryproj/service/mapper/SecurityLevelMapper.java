package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.SecurityLevelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SecurityLevel and its DTO SecurityLevelDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SecurityLevelMapper extends EntityMapper<SecurityLevelDTO, SecurityLevel> {


    @Mapping(target = "educationalModules", ignore = true)
    @Mapping(target = "requestEducationalModules", ignore = true)
    SecurityLevel toEntity(SecurityLevelDTO securityLevelDTO);

    default SecurityLevel fromId(Long id) {
        if (id == null) {
            return null;
        }
        SecurityLevel securityLevel = new SecurityLevel();
        securityLevel.setId(id);
        return securityLevel;
    }
}
