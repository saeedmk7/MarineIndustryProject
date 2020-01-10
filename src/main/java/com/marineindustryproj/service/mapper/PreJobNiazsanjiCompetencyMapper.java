package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.PreJobNiazsanjiCompetencyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PreJobNiazsanjiCompetency and its DTO PreJobNiazsanjiCompetencyDTO.
 */
@Mapper(componentModel = "spring", uses = {TeachingApproachMapper.class, FixCompetencyDeficiencyMapper.class, EducationalModuleMapper.class, PreJobNiazsanjiMapper.class, CompetencyMapper.class})
public interface PreJobNiazsanjiCompetencyMapper extends EntityMapper<PreJobNiazsanjiCompetencyDTO, PreJobNiazsanjiCompetency> {

    @Mapping(source = "fixCompetencyDeficiency.id", target = "fixCompetencyDeficiencyId")
    @Mapping(source = "fixCompetencyDeficiency.title", target = "fixCompetencyDeficiencyTitle")
    @Mapping(source = "educationalModule.id", target = "educationalModuleId")
    @Mapping(source = "educationalModule.title", target = "educationalModuleTitle")
    @Mapping(source = "preJobNiazsanji.id", target = "preJobNiazsanjiId")
    @Mapping(source = "preJobNiazsanji.title", target = "preJobNiazsanjiTitle")
    @Mapping(source = "competency.id", target = "competencyId")
    @Mapping(source = "competency.title", target = "competencyTitle")
    PreJobNiazsanjiCompetencyDTO toDto(PreJobNiazsanjiCompetency preJobNiazsanjiCompetency);

    @Mapping(target = "priorityCriteriaValues", ignore = true)
    @Mapping(source = "fixCompetencyDeficiencyId", target = "fixCompetencyDeficiency")
    @Mapping(source = "educationalModuleId", target = "educationalModule")
    @Mapping(source = "preJobNiazsanjiId", target = "preJobNiazsanji")
    @Mapping(source = "competencyId", target = "competency")
    PreJobNiazsanjiCompetency toEntity(PreJobNiazsanjiCompetencyDTO preJobNiazsanjiCompetencyDTO);

    default PreJobNiazsanjiCompetency fromId(Long id) {
        if (id == null) {
            return null;
        }
        PreJobNiazsanjiCompetency preJobNiazsanjiCompetency = new PreJobNiazsanjiCompetency();
        preJobNiazsanjiCompetency.setId(id);
        return preJobNiazsanjiCompetency;
    }
}
