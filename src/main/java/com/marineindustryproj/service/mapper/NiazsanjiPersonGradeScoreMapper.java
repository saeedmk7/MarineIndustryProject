package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.NiazsanjiPersonGradeScoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NiazsanjiPersonGradeScore and its DTO NiazsanjiPersonGradeScoreDTO.
 */
@Mapper(componentModel = "spring", uses = {NiazsanjiPersonCriteriaMapper.class, NiazsanjiPersonGradeMapper.class})
public interface NiazsanjiPersonGradeScoreMapper extends EntityMapper<NiazsanjiPersonGradeScoreDTO, NiazsanjiPersonGradeScore> {

    @Mapping(source = "niazsanjiPersonCriteria.id", target = "niazsanjiPersonCriteriaId")
    @Mapping(source = "niazsanjiPersonCriteria.title", target = "niazsanjiPersonCriteriaTitle")
    @Mapping(source = "niazsanjiPersonGrade.id", target = "niazsanjiPersonGradeId")
    @Mapping(source = "niazsanjiPersonGrade.title", target = "niazsanjiPersonGradeTitle")
    NiazsanjiPersonGradeScoreDTO toDto(NiazsanjiPersonGradeScore niazsanjiPersonGradeScore);

    @Mapping(source = "niazsanjiPersonCriteriaId", target = "niazsanjiPersonCriteria")
    @Mapping(source = "niazsanjiPersonGradeId", target = "niazsanjiPersonGrade")
    NiazsanjiPersonGradeScore toEntity(NiazsanjiPersonGradeScoreDTO niazsanjiPersonGradeScoreDTO);

    default NiazsanjiPersonGradeScore fromId(Long id) {
        if (id == null) {
            return null;
        }
        NiazsanjiPersonGradeScore niazsanjiPersonGradeScore = new NiazsanjiPersonGradeScore();
        niazsanjiPersonGradeScore.setId(id);
        return niazsanjiPersonGradeScore;
    }
}
