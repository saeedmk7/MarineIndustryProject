package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.CapitationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Capitation and its DTO CapitationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CapitationMapper extends EntityMapper<CapitationDTO, Capitation> {



    default Capitation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Capitation capitation = new Capitation();
        capitation.setId(id);
        return capitation;
    }
}
