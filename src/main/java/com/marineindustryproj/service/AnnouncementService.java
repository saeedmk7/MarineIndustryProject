package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.AnnouncementDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Announcement.
 */
public interface AnnouncementService {

    /**
     * Save a announcement.
     *
     * @param announcementDTO the entity to save
     * @return the persisted entity
     */
    AnnouncementDTO save(AnnouncementDTO announcementDTO);

    /**
     * Get all the announcements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AnnouncementDTO> findAll(Pageable pageable);

    /**
     * Get all the Announcement with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<AnnouncementDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" announcement.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AnnouncementDTO> findOne(Long id);

    /**
     * Delete the "id" announcement.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
