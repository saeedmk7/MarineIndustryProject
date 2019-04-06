package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.BeautySpeechDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BeautySpeech and its DTO BeautySpeechDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BeautySpeechMapper extends EntityMapper<BeautySpeechDTO, BeautySpeech> {



    default BeautySpeech fromId(Long id) {
        if (id == null) {
            return null;
        }
        BeautySpeech beautySpeech = new BeautySpeech();
        beautySpeech.setId(id);
        return beautySpeech;
    }
}
