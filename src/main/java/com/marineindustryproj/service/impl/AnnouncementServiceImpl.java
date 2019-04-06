package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.AnnouncementService;
import com.marineindustryproj.domain.Announcement;
import com.marineindustryproj.repository.AnnouncementRepository;
import com.marineindustryproj.service.dto.AnnouncementDTO;
import com.marineindustryproj.service.mapper.AnnouncementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Announcement.
 */
@Service
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService {

    private final Logger log = LoggerFactory.getLogger(AnnouncementServiceImpl.class);

    private final AnnouncementRepository announcementRepository;

    private final AnnouncementMapper announcementMapper;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository, AnnouncementMapper announcementMapper) {
        this.announcementRepository = announcementRepository;
        this.announcementMapper = announcementMapper;
    }

    /**
     * Save a announcement.
     *
     * @param announcementDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AnnouncementDTO save(AnnouncementDTO announcementDTO) {
        log.debug("Request to save Announcement : {}", announcementDTO);

        Announcement announcement = announcementMapper.toEntity(announcementDTO);
        announcement = announcementRepository.save(announcement);
        return announcementMapper.toDto(announcement);
    }

    /**
     * Get all the announcements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AnnouncementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Announcements");
        return announcementRepository.findAll(pageable)
            .map(announcementMapper::toDto);
    }

    /**
     * Get all the Announcement with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<AnnouncementDTO> findAllWithEagerRelationships(Pageable pageable) {
        return announcementRepository.findAllWithEagerRelationships(pageable).map(announcementMapper::toDto);
    }
    

    /**
     * Get one announcement by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AnnouncementDTO> findOne(Long id) {
        log.debug("Request to get Announcement : {}", id);
        return announcementRepository.findOneWithEagerRelationships(id)
            .map(announcementMapper::toDto);
    }

    /**
     * Delete the announcement by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Announcement : {}", id);
        announcementRepository.deleteById(id);
    }
}
