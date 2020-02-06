package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.DesignNiazsanjiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DesignNiazsanji and its DTO DesignNiazsanjiDTO.
 */
@Mapper(componentModel = "spring", uses = {RestrictionMapper.class, PreJobNiazsanjiMapper.class, CourseTypeMapper.class, EducationalModuleMapper.class, TeachingApproachMapper.class})
public interface DesignNiazsanjiMapper extends EntityMapper<DesignNiazsanjiDTO, DesignNiazsanji> {

    @Mapping(source = "preJobNiazsanji.id", target = "preJobNiazsanjiId")
    @Mapping(source = "preJobNiazsanji.title", target = "preJobNiazsanjiTitle")
    @Mapping(source = "courseType.id", target = "courseTypeId")
    @Mapping(source = "courseType.title", target = "courseTypeTitle")
    @Mapping(source = "educationalModule.id", target = "educationalModuleId")
    @Mapping(source = "educationalModule.code", target = "educationalModuleCode")
    @Mapping(source = "educationalModule.title", target = "educationalModuleTitle")
    @Mapping(source = "teachingApproach.id", target = "teachingApproachId")
    @Mapping(source = "teachingApproach.title", target = "teachingApproachTitle")
    DesignNiazsanjiDTO toDto(DesignNiazsanji designNiazsanji);

    @Mapping(source = "preJobNiazsanjiId", target = "preJobNiazsanji")
    @Mapping(source = "courseTypeId", target = "courseType")
    @Mapping(source = "educationalModuleId", target = "educationalModule")
    @Mapping(source = "teachingApproachId", target = "teachingApproach")
    DesignNiazsanji toEntity(DesignNiazsanjiDTO designNiazsanjiDTO);

    default DesignNiazsanji fromId(Long id) {
        if (id == null) {
            return null;
        }
        DesignNiazsanji designNiazsanji = new DesignNiazsanji();
        designNiazsanji.setId(id);
        return designNiazsanji;
    }
}
