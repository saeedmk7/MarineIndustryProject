package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.LevelThreeCriteriaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LevelThreeCriteria and its DTO LevelThreeCriteriaDTO.
 */
@Mapper(componentModel = "spring", uses = {MahiatCourseMapper.class, LevelThreeCriteriaGroupMapper.class})
public interface LevelThreeCriteriaMapper extends EntityMapper<LevelThreeCriteriaDTO, LevelThreeCriteria> {

    @Mapping(source = "mahiatCourse.id", target = "mahiatCourseId")
    @Mapping(source = "mahiatCourse.title", target = "mahiatCourseTitle")
    @Mapping(source = "levelThreeCriteriaGroup.id", target = "levelThreeCriteriaGroupId")
    @Mapping(source = "levelThreeCriteriaGroup.title", target = "levelThreeCriteriaGroupTitle")
    LevelThreeCriteriaDTO toDto(LevelThreeCriteria levelThreeCriteria);

    @Mapping(target = "levelThreeScores", ignore = true)
    @Mapping(source = "mahiatCourseId", target = "mahiatCourse")
    @Mapping(source = "levelThreeCriteriaGroupId", target = "levelThreeCriteriaGroup")
    LevelThreeCriteria toEntity(LevelThreeCriteriaDTO levelThreeCriteriaDTO);

    default LevelThreeCriteria fromId(Long id) {
        if (id == null) {
            return null;
        }
        LevelThreeCriteria levelThreeCriteria = new LevelThreeCriteria();
        levelThreeCriteria.setId(id);
        return levelThreeCriteria;
    }
}
