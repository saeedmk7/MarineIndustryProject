package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.AnnouncementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Announcement and its DTO AnnouncementDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class})
public interface AnnouncementMapper extends EntityMapper<AnnouncementDTO, Announcement> {



    default Announcement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Announcement announcement = new Announcement();
        announcement.setId(id);
        return announcement;
    }
}
