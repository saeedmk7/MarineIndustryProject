package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EducationalHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EducationalHistory and its DTO EducationalHistoryDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class, OrganizationChartMapper.class})
public interface EducationalHistoryMapper extends EntityMapper<EducationalHistoryDTO, EducationalHistory> {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.name", target = "personName")
    @Mapping(source = "person.family", target = "personFamily")
    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    EducationalHistoryDTO toDto(EducationalHistory educationalHistory);

    @Mapping(source = "personId", target = "person")
    @Mapping(source = "organizationChartId", target = "organizationChart")
    EducationalHistory toEntity(EducationalHistoryDTO educationalHistoryDTO);

    default EducationalHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        EducationalHistory educationalHistory = new EducationalHistory();
        educationalHistory.setId(id);
        return educationalHistory;
    }
}
