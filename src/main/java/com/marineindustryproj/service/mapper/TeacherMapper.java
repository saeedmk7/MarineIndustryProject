package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.TeacherDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Teacher and its DTO TeacherDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, QualificationMapper.class, FieldOfStudyMapper.class, ServiceUnitMapper.class, AcademicRankMapper.class})
public interface TeacherMapper extends EntityMapper<TeacherDTO, Teacher> {

    @Mapping(source = "lastQualification.id", target = "lastQualificationId")
    @Mapping(source = "lastQualification.title", target = "lastQualificationTitle")
    @Mapping(source = "lastFieldOfStudy.id", target = "lastFieldOfStudyId")
    @Mapping(source = "lastFieldOfStudy.title", target = "lastFieldOfStudyTitle")
    @Mapping(source = "serviceUnit.id", target = "serviceUnitId")
    @Mapping(source = "serviceUnit.title", target = "serviceUnitTitle")
    @Mapping(source = "academicRank.id", target = "academicRankId")
    @Mapping(source = "academicRank.title", target = "academicRankTitle")
    TeacherDTO toDto(Teacher teacher);

    @Mapping(target = "requestOrganizationNiazsanjis", ignore = true)
    @Mapping(target = "finalOrganizationNiazsanjis", ignore = true)
    @Mapping(source = "lastQualificationId", target = "lastQualification")
    @Mapping(source = "lastFieldOfStudyId", target = "lastFieldOfStudy")
    @Mapping(source = "serviceUnitId", target = "serviceUnit")
    @Mapping(source = "academicRankId", target = "academicRank")
    @Mapping(target = "educationalModules", ignore = true)
    @Mapping(target = "requestEducationalModules", ignore = true)
    Teacher toEntity(TeacherDTO teacherDTO);

    default Teacher fromId(Long id) {
        if (id == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setId(id);
        return teacher;
    }
}
