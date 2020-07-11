package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.ReportGeneratorAuthorityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ReportGeneratorAuthority and its DTO ReportGeneratorAuthorityDTO.
 */
@Mapper(componentModel = "spring", uses = {ReportGeneratorMapper.class})
public interface ReportGeneratorAuthorityMapper extends EntityMapper<ReportGeneratorAuthorityDTO, ReportGeneratorAuthority> {

    @Mapping(source = "reportGenerator.id", target = "reportGeneratorId")
    @Mapping(source = "reportGenerator.title", target = "reportGeneratorTitle")
    ReportGeneratorAuthorityDTO toDto(ReportGeneratorAuthority reportGeneratorAuthority);

    @Mapping(source = "reportGeneratorId", target = "reportGenerator")
    ReportGeneratorAuthority toEntity(ReportGeneratorAuthorityDTO reportGeneratorAuthorityDTO);

    default ReportGeneratorAuthority fromId(Long id) {
        if (id == null) {
            return null;
        }
        ReportGeneratorAuthority reportGeneratorAuthority = new ReportGeneratorAuthority();
        reportGeneratorAuthority.setId(id);
        return reportGeneratorAuthority;
    }
}
