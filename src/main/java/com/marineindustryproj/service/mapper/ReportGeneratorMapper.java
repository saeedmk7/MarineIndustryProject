package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.ReportGeneratorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ReportGenerator and its DTO ReportGeneratorDTO.
 */
@Mapper(componentModel = "spring", uses = {OrganizationChartMapper.class})
public interface ReportGeneratorMapper extends EntityMapper<ReportGeneratorDTO, ReportGenerator> {


    @Mapping(target = "reportGeneratorAuthorities", ignore = true)
    ReportGenerator toEntity(ReportGeneratorDTO reportGeneratorDTO);

    default ReportGenerator fromId(Long id) {
        if (id == null) {
            return null;
        }
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.setId(id);
        return reportGenerator;
    }
}
