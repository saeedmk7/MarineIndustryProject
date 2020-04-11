package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.LevelThreeEffectivenessDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LevelThreeEffectiveness and its DTO LevelThreeEffectivenessDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, FinalNiazsanjiReportPersonMapper.class})
public interface LevelThreeEffectivenessMapper extends EntityMapper<LevelThreeEffectivenessDTO, LevelThreeEffectiveness> {

    @Mapping(source = "finalNiazsanjiReportPerson.id", target = "finalNiazsanjiReportPersonId")
    @Mapping(source = "finalNiazsanjiReportPerson.description", target = "finalNiazsanjiReportPersonDescription")
    LevelThreeEffectivenessDTO toDto(LevelThreeEffectiveness levelThreeEffectiveness);

    @Mapping(target = "levelThreeScores", ignore = true)
    @Mapping(source = "finalNiazsanjiReportPersonId", target = "finalNiazsanjiReportPerson")
    LevelThreeEffectiveness toEntity(LevelThreeEffectivenessDTO levelThreeEffectivenessDTO);

    default LevelThreeEffectiveness fromId(Long id) {
        if (id == null) {
            return null;
        }
        LevelThreeEffectiveness levelThreeEffectiveness = new LevelThreeEffectiveness();
        levelThreeEffectiveness.setId(id);
        return levelThreeEffectiveness;
    }
}
