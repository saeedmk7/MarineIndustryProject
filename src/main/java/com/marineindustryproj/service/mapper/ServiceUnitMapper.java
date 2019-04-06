package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.ServiceUnitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ServiceUnit and its DTO ServiceUnitDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ServiceUnitMapper extends EntityMapper<ServiceUnitDTO, ServiceUnit> {


    @Mapping(target = "teachers", ignore = true)
    ServiceUnit toEntity(ServiceUnitDTO serviceUnitDTO);

    default ServiceUnit fromId(Long id) {
        if (id == null) {
            return null;
        }
        ServiceUnit serviceUnit = new ServiceUnit();
        serviceUnit.setId(id);
        return serviceUnit;
    }
}
