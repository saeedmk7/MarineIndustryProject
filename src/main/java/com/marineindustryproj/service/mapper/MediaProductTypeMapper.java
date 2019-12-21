package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.MediaProductTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MediaProductType and its DTO MediaProductTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MediaProductTypeMapper extends EntityMapper<MediaProductTypeDTO, MediaProductType> {


    @Mapping(target = "mediaAwarenessReports", ignore = true)
    MediaProductType toEntity(MediaProductTypeDTO mediaProductTypeDTO);

    default MediaProductType fromId(Long id) {
        if (id == null) {
            return null;
        }
        MediaProductType mediaProductType = new MediaProductType();
        mediaProductType.setId(id);
        return mediaProductType;
    }
}
