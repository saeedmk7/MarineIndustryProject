package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.OrganizationChartAuthorityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrganizationChartAuthority and its DTO OrganizationChartAuthorityDTO.
 */
@Mapper(componentModel = "spring", uses = {OrganizationChartMapper.class})
public interface OrganizationChartAuthorityMapper extends EntityMapper<OrganizationChartAuthorityDTO, OrganizationChartAuthority> {

    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    OrganizationChartAuthorityDTO toDto(OrganizationChartAuthority organizationChartAuthority);

    @Mapping(source = "organizationChartId", target = "organizationChart")
    OrganizationChartAuthority toEntity(OrganizationChartAuthorityDTO organizationChartAuthorityDTO);

    default OrganizationChartAuthority fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrganizationChartAuthority organizationChartAuthority = new OrganizationChartAuthority();
        organizationChartAuthority.setId(id);
        return organizationChartAuthority;
    }
}
