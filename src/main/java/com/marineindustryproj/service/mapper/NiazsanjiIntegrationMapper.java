package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.NiazsanjiIntegrationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NiazsanjiIntegration and its DTO NiazsanjiIntegrationDTO.
 */
@Mapper(componentModel = "spring", uses = {PrioritizeRequestNiazsanjiMapper.class})
public interface NiazsanjiIntegrationMapper extends EntityMapper<NiazsanjiIntegrationDTO, NiazsanjiIntegration> {

    @Mapping(source = "prioritizeRequestNiazsanji.id", target = "prioritizeRequestNiazsanjiId")
    @Mapping(source = "prioritizeRequestNiazsanji.code", target = "prioritizeRequestNiazsanjiCode")
    NiazsanjiIntegrationDTO toDto(NiazsanjiIntegration niazsanjiIntegration);

    @Mapping(target = "finalNiazsanjiReports", ignore = true)
    @Mapping(source = "prioritizeRequestNiazsanjiId", target = "prioritizeRequestNiazsanji")
    NiazsanjiIntegration toEntity(NiazsanjiIntegrationDTO niazsanjiIntegrationDTO);

    default NiazsanjiIntegration fromId(Long id) {
        if (id == null) {
            return null;
        }
        NiazsanjiIntegration niazsanjiIntegration = new NiazsanjiIntegration();
        niazsanjiIntegration.setId(id);
        return niazsanjiIntegration;
    }
}
