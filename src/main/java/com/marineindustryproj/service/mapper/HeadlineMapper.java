package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.HeadlineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Headline and its DTO HeadlineDTO.
 */
@Mapper(componentModel = "spring", uses = {RequestEducationalModuleMapper.class, EducationalModuleMapper.class})
public interface HeadlineMapper extends EntityMapper<HeadlineDTO, Headline> {

    @Mapping(source = "requestEducationalModule.id", target = "requestEducationalModuleId")
    @Mapping(source = "requestEducationalModule.title", target = "requestEducationalModuleTitle")
    @Mapping(source = "educationalModule.id", target = "educationalModuleId")
    @Mapping(source = "educationalModule.title", target = "educationalModuleTitle")
    HeadlineDTO toDto(Headline headline);

    @Mapping(source = "requestEducationalModuleId", target = "requestEducationalModule")
    @Mapping(source = "educationalModuleId", target = "educationalModule")
    Headline toEntity(HeadlineDTO headlineDTO);

    default Headline fromId(Long id) {
        if (id == null) {
            return null;
        }
        Headline headline = new Headline();
        headline.setId(id);
        return headline;
    }
}
