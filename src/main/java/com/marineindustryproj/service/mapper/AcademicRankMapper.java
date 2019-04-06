package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.AcademicRankDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AcademicRank and its DTO AcademicRankDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AcademicRankMapper extends EntityMapper<AcademicRankDTO, AcademicRank> {


    @Mapping(target = "teachers", ignore = true)
    AcademicRank toEntity(AcademicRankDTO academicRankDTO);

    default AcademicRank fromId(Long id) {
        if (id == null) {
            return null;
        }
        AcademicRank academicRank = new AcademicRank();
        academicRank.setId(id);
        return academicRank;
    }
}
