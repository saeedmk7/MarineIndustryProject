package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.BeautySpeechAuthorityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BeautySpeechAuthority and its DTO BeautySpeechAuthorityDTO.
 */
@Mapper(componentModel = "spring", uses = {BeautySpeechMapper.class})
public interface BeautySpeechAuthorityMapper extends EntityMapper<BeautySpeechAuthorityDTO, BeautySpeechAuthority> {

    @Mapping(source = "beautySpeech.id", target = "beautySpeechId")
    @Mapping(source = "beautySpeech.title", target = "beautySpeechTitle")
    BeautySpeechAuthorityDTO toDto(BeautySpeechAuthority beautySpeechAuthority);

    @Mapping(source = "beautySpeechId", target = "beautySpeech")
    BeautySpeechAuthority toEntity(BeautySpeechAuthorityDTO beautySpeechAuthorityDTO);

    default BeautySpeechAuthority fromId(Long id) {
        if (id == null) {
            return null;
        }
        BeautySpeechAuthority beautySpeechAuthority = new BeautySpeechAuthority();
        beautySpeechAuthority.setId(id);
        return beautySpeechAuthority;
    }
}
