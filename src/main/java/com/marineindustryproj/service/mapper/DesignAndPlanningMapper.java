package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.DesignAndPlanningDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DesignAndPlanning and its DTO DesignAndPlanningDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class, DocumentMapper.class, FinalNiazsanjiReportMapper.class, MahiatCourseMapper.class, CourseTypeMapper.class, TeachTypeMapper.class, CourseLocationMapper.class, ConditionsOfParticipantMapper.class, EffectivenessLevelMapper.class, ToolsAndFacilityMapper.class, TeachingApproachMapper.class, TeachTechniqueMapper.class, EffectivenessIndexMapper.class})
public interface DesignAndPlanningMapper extends EntityMapper<DesignAndPlanningDTO, DesignAndPlanning> {

    @Mapping(source = "finalNiazsanjiReport.id", target = "finalNiazsanjiReportId")
    @Mapping(source = "finalNiazsanjiReport.description", target = "finalNiazsanjiReportDescription")
    @Mapping(source = "mahiatCourse.id", target = "mahiatCourseId")
    @Mapping(source = "mahiatCourse.title", target = "mahiatCourseTitle")
    @Mapping(source = "courseType.id", target = "courseTypeId")
    @Mapping(source = "courseType.title", target = "courseTypeTitle")
    @Mapping(source = "teachType.id", target = "teachTypeId")
    @Mapping(source = "teachType.title", target = "teachTypeTitle")
    @Mapping(source = "courseLocation.id", target = "courseLocationId")
    @Mapping(source = "courseLocation.title", target = "courseLocationTitle")
    @Mapping(source = "conditionsOfParticipant.id", target = "conditionsOfParticipantId")
    @Mapping(source = "conditionsOfParticipant.title", target = "conditionsOfParticipantTitle")
    @Mapping(source = "effectivenessLevel.id", target = "effectivenessLevelId")
    @Mapping(source = "effectivenessLevel.title", target = "effectivenessLevelTitle")
    @Mapping(source = "toolsAndFacility.id", target = "toolsAndFacilityId")
    @Mapping(source = "toolsAndFacility.title", target = "toolsAndFacilityTitle")
    @Mapping(source = "teachingApproach.id", target = "teachingApproachId")
    @Mapping(source = "teachingApproach.title", target = "teachingApproachTitle")
    @Mapping(source = "teachTechnique.id", target = "teachTechniqueId")
    @Mapping(source = "teachTechnique.title", target = "teachTechniqueTitle")
    @Mapping(source = "effectivenessIndex.id", target = "effectivenessIndexId")
    @Mapping(source = "effectivenessIndex.title", target = "effectivenessIndexTitle")
    DesignAndPlanningDTO toDto(DesignAndPlanning designAndPlanning);

    @Mapping(source = "finalNiazsanjiReportId", target = "finalNiazsanjiReport")
    @Mapping(source = "mahiatCourseId", target = "mahiatCourse")
    @Mapping(source = "courseTypeId", target = "courseType")
    @Mapping(source = "teachTypeId", target = "teachType")
    @Mapping(source = "courseLocationId", target = "courseLocation")
    @Mapping(source = "conditionsOfParticipantId", target = "conditionsOfParticipant")
    @Mapping(source = "effectivenessLevelId", target = "effectivenessLevel")
    @Mapping(source = "toolsAndFacilityId", target = "toolsAndFacility")
    @Mapping(source = "teachingApproachId", target = "teachingApproach")
    @Mapping(source = "teachTechniqueId", target = "teachTechnique")
    @Mapping(source = "effectivenessIndexId", target = "effectivenessIndex")
    DesignAndPlanning toEntity(DesignAndPlanningDTO designAndPlanningDTO);

    default DesignAndPlanning fromId(Long id) {
        if (id == null) {
            return null;
        }
        DesignAndPlanning designAndPlanning = new DesignAndPlanning();
        designAndPlanning.setId(id);
        return designAndPlanning;
    }
}
