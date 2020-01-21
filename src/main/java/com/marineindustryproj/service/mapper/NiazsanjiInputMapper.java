package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.NiazsanjiInputDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NiazsanjiInput and its DTO NiazsanjiInputDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NiazsanjiInputMapper extends EntityMapper<NiazsanjiInputDTO, NiazsanjiInput> {


    @Mapping(target = "finalNiazsanjiReports", ignore = true)
    @Mapping(target = "niazsanjiOthers", ignore = true)
    @Mapping(target = "requestOtherNiazsanjis", ignore = true)
    @Mapping(target = "prioritizeRequestNiazsanjis", ignore = true)
    NiazsanjiInput toEntity(NiazsanjiInputDTO niazsanjiInputDTO);

    default NiazsanjiInput fromId(Long id) {
        if (id == null) {
            return null;
        }
        NiazsanjiInput niazsanjiInput = new NiazsanjiInput();
        niazsanjiInput.setId(id);
        return niazsanjiInput;
    }
}
