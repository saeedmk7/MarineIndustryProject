package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.NiazsanjiGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NiazsanjiGroup and its DTO NiazsanjiGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {JobMapper.class, EducationalModuleMapper.class, ScientificWorkGroupMapper.class})
public interface NiazsanjiGroupMapper extends EntityMapper<NiazsanjiGroupDTO, NiazsanjiGroup> {

    @Mapping(source = "scientificWorkGroup.id", target = "scientificWorkGroupId")
    @Mapping(source = "scientificWorkGroup.title", target = "scientificWorkGroupTitle")
    NiazsanjiGroupDTO toDto(NiazsanjiGroup niazsanjiGroup);

    @Mapping(source = "scientificWorkGroupId", target = "scientificWorkGroup")
    NiazsanjiGroup toEntity(NiazsanjiGroupDTO niazsanjiGroupDTO);

    default NiazsanjiGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        NiazsanjiGroup niazsanjiGroup = new NiazsanjiGroup();
        niazsanjiGroup.setId(id);
        return niazsanjiGroup;
    }
}
