package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.SoldierTrainingReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SoldierTrainingReport and its DTO SoldierTrainingReportDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, SoldierMapper.class})
public interface SoldierTrainingReportMapper extends EntityMapper<SoldierTrainingReportDTO, SoldierTrainingReport> {

    @Mapping(source = "soldier.id", target = "soldierId")
    @Mapping(source = "soldier.family", target = "soldierFamily")
    @Mapping(source = "soldier.name", target = "soldierName")
    @Mapping(source = "soldier.employmentDate", target = "soldierEmploymentDate")
    @Mapping(source = "soldier.nationalId", target = "soldierNationalId")
    @Mapping(source = "soldier.organizationChart.id", target = "soldierOrganizationChartId")
    @Mapping(source = "soldier.organizationChart.title", target = "soldierOrganizationChartTitle")
    SoldierTrainingReportDTO toDto(SoldierTrainingReport soldierTrainingReport);

    @Mapping(source = "soldierId", target = "soldier")
    SoldierTrainingReport toEntity(SoldierTrainingReportDTO soldierTrainingReportDTO);

    default SoldierTrainingReport fromId(Long id) {
        if (id == null) {
            return null;
        }
        SoldierTrainingReport soldierTrainingReport = new SoldierTrainingReport();
        soldierTrainingReport.setId(id);
        return soldierTrainingReport;
    }
}
