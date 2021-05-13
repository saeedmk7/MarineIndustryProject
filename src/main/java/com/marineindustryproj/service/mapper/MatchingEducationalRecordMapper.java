package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.MatchingEducationalRecordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MatchingEducationalRecord and its DTO MatchingEducationalRecordDTO.
 */
@Mapper(componentModel = "spring", uses = {SkillableLevelOfSkillMapper.class, DocumentMapper.class, PersonMapper.class, OrganizationChartMapper.class})
public interface MatchingEducationalRecordMapper extends EntityMapper<MatchingEducationalRecordDTO, MatchingEducationalRecord> {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.family", target = "personFamily")
    @Mapping(source = "person.name", target = "personName")
    @Mapping(source = "person.job.title", target = "personJobTitle")
    @Mapping(source = "person.employmentType.title", target = "personEmploymentTypeTitle")
    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    MatchingEducationalRecordDTO toDto(MatchingEducationalRecord matchingEducationalRecord);

    @Mapping(target = "matchingRunRunningSteps", ignore = true)
    @Mapping(source = "personId", target = "person")
    @Mapping(source = "organizationChartId", target = "organizationChart")
    MatchingEducationalRecord toEntity(MatchingEducationalRecordDTO matchingEducationalRecordDTO);

    default MatchingEducationalRecord fromId(Long id) {
        if (id == null) {
            return null;
        }
        MatchingEducationalRecord matchingEducationalRecord = new MatchingEducationalRecord();
        matchingEducationalRecord.setId(id);
        return matchingEducationalRecord;
    }
}
