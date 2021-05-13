package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.EducationalRecordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EducationalRecord and its DTO EducationalRecordDTO.
 */
@Mapper(componentModel = "spring", uses = {QualificationMapper.class, FieldOfStudyMapper.class, PersonMapper.class})
public interface EducationalRecordMapper extends EntityMapper<EducationalRecordDTO, EducationalRecord> {

    @Mapping(source = "qualification.id", target = "qualificationId")
    @Mapping(source = "qualification.title", target = "qualificationTitle")
    @Mapping(source = "fieldOfStudy.id", target = "fieldOfStudyId")
    @Mapping(source = "fieldOfStudy.title", target = "fieldOfStudyTitle")
    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.family", target = "personFamily")
    EducationalRecordDTO toDto(EducationalRecord educationalRecord);

    @Mapping(target = "applicationProcesses", ignore = true)
    @Mapping(source = "qualificationId", target = "qualification")
    @Mapping(source = "fieldOfStudyId", target = "fieldOfStudy")
    @Mapping(source = "personId", target = "person")
    EducationalRecord toEntity(EducationalRecordDTO educationalRecordDTO);

    default EducationalRecord fromId(Long id) {
        if (id == null) {
            return null;
        }
        EducationalRecord educationalRecord = new EducationalRecord();
        educationalRecord.setId(id);
        return educationalRecord;
    }
}
