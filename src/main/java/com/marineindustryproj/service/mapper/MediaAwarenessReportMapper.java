package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.MediaAwarenessReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MediaAwarenessReport and its DTO MediaAwarenessReportDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, OrganizationChartMapper.class, MediaProductTypeMapper.class})
public interface MediaAwarenessReportMapper extends EntityMapper<MediaAwarenessReportDTO, MediaAwarenessReport> {

    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    @Mapping(source = "mediaProductType.id", target = "mediaProductTypeId")
    @Mapping(source = "mediaProductType.title", target = "mediaProductTypeTitle")
    MediaAwarenessReportDTO toDto(MediaAwarenessReport mediaAwarenessReport);

    @Mapping(source = "organizationChartId", target = "organizationChart")
    @Mapping(source = "mediaProductTypeId", target = "mediaProductType")
    MediaAwarenessReport toEntity(MediaAwarenessReportDTO mediaAwarenessReportDTO);

    default MediaAwarenessReport fromId(Long id) {
        if (id == null) {
            return null;
        }
        MediaAwarenessReport mediaAwarenessReport = new MediaAwarenessReport();
        mediaAwarenessReport.setId(id);
        return mediaAwarenessReport;
    }
}
