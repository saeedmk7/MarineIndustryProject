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
    @Mapping(source = "prioritizeRequestNiazsanji.costEducationalModule", target = "costEducationalModule")
    @Mapping(source = "prioritizeRequestNiazsanji.educationalModuleType", target = "educationalModuleType")
    @Mapping(source = "prioritizeRequestNiazsanji.niazsanjiInput.title", target = "niazsanjiInputTitle")
    @Mapping(source = "prioritizeRequestNiazsanji.courseType.title", target = "courseTypeTitle")
    @Mapping(source = "prioritizeRequestNiazsanji.educationalModule.id", target = "educationalModuleId")
    @Mapping(source = "prioritizeRequestNiazsanji.educationalModule.code", target = "educationalModuleCode")
    @Mapping(source = "prioritizeRequestNiazsanji.educationalModule.title", target = "educationalModuleTitle")
    @Mapping(source = "prioritizeRequestNiazsanji.person.family", target = "personFamily")
    @Mapping(source = "prioritizeRequestNiazsanji.person.name", target = "personName")
    @Mapping(source = "prioritizeRequestNiazsanji.organizationChart.id", target = "organizationChartId")
    @Mapping(source = "prioritizeRequestNiazsanji.organizationChart.title", target = "organizationChartTitle")
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
