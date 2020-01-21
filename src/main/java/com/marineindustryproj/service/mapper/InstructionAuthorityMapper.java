package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.InstructionAuthorityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity InstructionAuthority and its DTO InstructionAuthorityDTO.
 */
@Mapper(componentModel = "spring", uses = {InstructionMapper.class})
public interface InstructionAuthorityMapper extends EntityMapper<InstructionAuthorityDTO, InstructionAuthority> {

    @Mapping(source = "instruction.id", target = "instructionId")
    @Mapping(source = "instruction.title", target = "instructionTitle")
    InstructionAuthorityDTO toDto(InstructionAuthority instructionAuthority);

    @Mapping(source = "instructionId", target = "instruction")
    InstructionAuthority toEntity(InstructionAuthorityDTO instructionAuthorityDTO);

    default InstructionAuthority fromId(Long id) {
        if (id == null) {
            return null;
        }
        InstructionAuthority instructionAuthority = new InstructionAuthority();
        instructionAuthority.setId(id);
        return instructionAuthority;
    }
}
