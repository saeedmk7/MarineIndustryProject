package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.ActivationNiazsanjiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ActivationNiazsanji and its DTO ActivationNiazsanjiDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ActivationNiazsanjiMapper extends EntityMapper<ActivationNiazsanjiDTO, ActivationNiazsanji> {



    default ActivationNiazsanji fromId(Long id) {
        if (id == null) {
            return null;
        }
        ActivationNiazsanji activationNiazsanji = new ActivationNiazsanji();
        activationNiazsanji.setId(id);
        return activationNiazsanji;
    }
}
