package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportPersonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FinalNiazsanjiReportPerson and its DTO FinalNiazsanjiReportPersonDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class, FinalNiazsanjiReportMapper.class})
public interface FinalNiazsanjiReportPersonMapper extends EntityMapper<FinalNiazsanjiReportPersonDTO, FinalNiazsanjiReportPerson> {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.family", target = "personFamily")
    @Mapping(source = "finalNiazsanjiReport.id", target = "finalNiazsanjiReportId")
    @Mapping(source = "finalNiazsanjiReport.description", target = "finalNiazsanjiReportDescription")
    FinalNiazsanjiReportPersonDTO toDto(FinalNiazsanjiReportPerson finalNiazsanjiReportPerson);

    @Mapping(source = "personId", target = "person")
    @Mapping(source = "finalNiazsanjiReportId", target = "finalNiazsanjiReport")
    FinalNiazsanjiReportPerson toEntity(FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPersonDTO);

    default FinalNiazsanjiReportPerson fromId(Long id) {
        if (id == null) {
            return null;
        }
        FinalNiazsanjiReportPerson finalNiazsanjiReportPerson = new FinalNiazsanjiReportPerson();
        finalNiazsanjiReportPerson.setId(id);
        return finalNiazsanjiReportPerson;
    }
}
