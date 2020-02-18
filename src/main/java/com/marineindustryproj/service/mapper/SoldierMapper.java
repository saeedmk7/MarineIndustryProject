package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.SoldierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Soldier and its DTO SoldierDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, QualificationMapper.class, FieldOfStudyMapper.class, JobMapper.class, OrganizationChartMapper.class})
public interface SoldierMapper extends EntityMapper<SoldierDTO, Soldier> {

    @Mapping(source = "lastQualification.id", target = "lastQualificationId")
    @Mapping(source = "lastQualification.title", target = "lastQualificationTitle")
    @Mapping(source = "lastFieldOfStudy.id", target = "lastFieldOfStudyId")
    @Mapping(source = "lastFieldOfStudy.title", target = "lastFieldOfStudyTitle")
    @Mapping(source = "job.id", target = "jobId")
    @Mapping(source = "job.title", target = "jobTitle")
    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    SoldierDTO toDto(Soldier soldier);

    @Mapping(target = "soldierTrainingReports", ignore = true)
    @Mapping(source = "lastQualificationId", target = "lastQualification")
    @Mapping(source = "lastFieldOfStudyId", target = "lastFieldOfStudy")
    @Mapping(source = "jobId", target = "job")
    @Mapping(source = "organizationChartId", target = "organizationChart")
    Soldier toEntity(SoldierDTO soldierDTO);

    default Soldier fromId(Long id) {
        if (id == null) {
            return null;
        }
        Soldier soldier = new Soldier();
        soldier.setId(id);
        return soldier;
    }
}
