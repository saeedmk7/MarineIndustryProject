package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.PreJobNiazsanjiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PreJobNiazsanji and its DTO PreJobNiazsanjiDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, PersonMapper.class, OrganizationChartMapper.class})
public interface PreJobNiazsanjiMapper extends EntityMapper<PreJobNiazsanjiDTO, PreJobNiazsanji> {

    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.family", target = "personFamily")
    PreJobNiazsanjiDTO toDto(PreJobNiazsanji preJobNiazsanji);

    @Mapping(target = "niazsanjiFardis", ignore = true)
    @Mapping(target = "designNiazsanjis", ignore = true)
    @Mapping(target = "preJobNiazsanjiCompetencies", ignore = true)
    @Mapping(target = "jobNiazsanjis", ignore = true)
    @Mapping(source = "organizationChartId", target = "organizationChart")
    @Mapping(source = "personId", target = "person")
    PreJobNiazsanji toEntity(PreJobNiazsanjiDTO preJobNiazsanjiDTO);

    default PreJobNiazsanji fromId(Long id) {
        if (id == null) {
            return null;
        }
        PreJobNiazsanji preJobNiazsanji = new PreJobNiazsanji();
        preJobNiazsanji.setId(id);
        return preJobNiazsanji;
    }
}
