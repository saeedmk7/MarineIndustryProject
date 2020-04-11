package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.NiazsanjiPersonCriteriaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NiazsanjiPersonCriteria and its DTO NiazsanjiPersonCriteriaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NiazsanjiPersonCriteriaMapper extends EntityMapper<NiazsanjiPersonCriteriaDTO, NiazsanjiPersonCriteria> {


    @Mapping(target = "niazsanjiPersonGradeScores", ignore = true)
    NiazsanjiPersonCriteria toEntity(NiazsanjiPersonCriteriaDTO niazsanjiPersonCriteriaDTO);

    default NiazsanjiPersonCriteria fromId(Long id) {
        if (id == null) {
            return null;
        }
        NiazsanjiPersonCriteria niazsanjiPersonCriteria = new NiazsanjiPersonCriteria();
        niazsanjiPersonCriteria.setId(id);
        return niazsanjiPersonCriteria;
    }
}
