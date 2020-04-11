package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.LevelFourEffectivenessDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LevelFourEffectiveness and its DTO LevelFourEffectivenessDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, FinalNiazsanjiReportPersonMapper.class})
public interface LevelFourEffectivenessMapper extends EntityMapper<LevelFourEffectivenessDTO, LevelFourEffectiveness> {

    @Mapping(source = "finalNiazsanjiReportPerson.id", target = "finalNiazsanjiReportPersonId")
    @Mapping(source = "finalNiazsanjiReportPerson.description", target = "finalNiazsanjiReportPersonDescription")
    LevelFourEffectivenessDTO toDto(LevelFourEffectiveness levelFourEffectiveness);

    @Mapping(target = "levelFourScores", ignore = true)
    @Mapping(source = "finalNiazsanjiReportPersonId", target = "finalNiazsanjiReportPerson")
    LevelFourEffectiveness toEntity(LevelFourEffectivenessDTO levelFourEffectivenessDTO);

    default LevelFourEffectiveness fromId(Long id) {
        if (id == null) {
            return null;
        }
        LevelFourEffectiveness levelFourEffectiveness = new LevelFourEffectiveness();
        levelFourEffectiveness.setId(id);
        return levelFourEffectiveness;
    }
}
