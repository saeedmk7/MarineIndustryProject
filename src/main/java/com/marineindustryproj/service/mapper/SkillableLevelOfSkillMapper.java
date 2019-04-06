package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.SkillableLevelOfSkillDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SkillableLevelOfSkill and its DTO SkillableLevelOfSkillDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SkillableLevelOfSkillMapper extends EntityMapper<SkillableLevelOfSkillDTO, SkillableLevelOfSkill> {


    @Mapping(target = "educationalModules", ignore = true)
    @Mapping(target = "requestEducationalModules", ignore = true)
    SkillableLevelOfSkill toEntity(SkillableLevelOfSkillDTO skillableLevelOfSkillDTO);

    default SkillableLevelOfSkill fromId(Long id) {
        if (id == null) {
            return null;
        }
        SkillableLevelOfSkill skillableLevelOfSkill = new SkillableLevelOfSkill();
        skillableLevelOfSkill.setId(id);
        return skillableLevelOfSkill;
    }
}
