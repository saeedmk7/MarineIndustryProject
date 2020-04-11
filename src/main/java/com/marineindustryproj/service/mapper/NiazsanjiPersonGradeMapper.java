package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.NiazsanjiPersonGradeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NiazsanjiPersonGrade and its DTO NiazsanjiPersonGradeDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, FinalNiazsanjiReportPersonMapper.class})
public interface NiazsanjiPersonGradeMapper extends EntityMapper<NiazsanjiPersonGradeDTO, NiazsanjiPersonGrade> {

    @Mapping(source = "finalNiazsanjiReportPerson.id", target = "finalNiazsanjiReportPersonId")
    /*@Mapping(source = "finalNiazsanjiReportPerson.person", target = "finalNiazsanjiReportPersonPerson")*/
    NiazsanjiPersonGradeDTO toDto(NiazsanjiPersonGrade niazsanjiPersonGrade);

    @Mapping(target = "niazsanjiPersonGradeScores", ignore = true)
    @Mapping(source = "finalNiazsanjiReportPersonId", target = "finalNiazsanjiReportPerson")
    NiazsanjiPersonGrade toEntity(NiazsanjiPersonGradeDTO niazsanjiPersonGradeDTO);

    default NiazsanjiPersonGrade fromId(Long id) {
        if (id == null) {
            return null;
        }
        NiazsanjiPersonGrade niazsanjiPersonGrade = new NiazsanjiPersonGrade();
        niazsanjiPersonGrade.setId(id);
        return niazsanjiPersonGrade;
    }
}
