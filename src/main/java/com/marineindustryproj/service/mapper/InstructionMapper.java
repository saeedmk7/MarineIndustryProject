package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.InstructionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Instruction and its DTO InstructionDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class})
public interface InstructionMapper extends EntityMapper<InstructionDTO, Instruction> {


    @Mapping(target = "instructionAuthorities", ignore = true)
    Instruction toEntity(InstructionDTO instructionDTO);

    default Instruction fromId(Long id) {
        if (id == null) {
            return null;
        }
        Instruction instruction = new Instruction();
        instruction.setId(id);
        return instruction;
    }
}
