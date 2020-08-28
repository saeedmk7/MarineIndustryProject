package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.SoldierMediaAwarenessReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SoldierMediaAwarenessReport and its DTO SoldierMediaAwarenessReportDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, SoldierMapper.class})
public interface SoldierMediaAwarenessReportMapper extends EntityMapper<SoldierMediaAwarenessReportDTO, SoldierMediaAwarenessReport> {

    @Mapping(source = "soldier.id", target = "soldierId")
    @Mapping(source = "soldier.family", target = "soldierFamily")
    @Mapping(source = "soldier.name", target = "soldierName")
    @Mapping(source = "soldier.employmentDate", target = "soldierEmploymentDate")
    @Mapping(source = "soldier.nationalId", target = "soldierNationalId")
    @Mapping(source = "soldier.organizationChart.id", target = "soldierOrganizationChartId")
    @Mapping(source = "soldier.organizationChart.title", target = "soldierOrganizationChartTitle")
    SoldierMediaAwarenessReportDTO toDto(SoldierMediaAwarenessReport soldierMediaAwarenessReport);

    @Mapping(source = "soldierId", target = "soldier")
    SoldierMediaAwarenessReport toEntity(SoldierMediaAwarenessReportDTO soldierMediaAwarenessReportDTO);

    default SoldierMediaAwarenessReport fromId(Long id) {
        if (id == null) {
            return null;
        }
        SoldierMediaAwarenessReport soldierMediaAwarenessReport = new SoldierMediaAwarenessReport();
        soldierMediaAwarenessReport.setId(id);
        return soldierMediaAwarenessReport;
    }
}
