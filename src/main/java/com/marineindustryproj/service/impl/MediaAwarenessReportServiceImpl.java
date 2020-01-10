package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.MediaAwarenessReportService;
import com.marineindustryproj.domain.MediaAwarenessReport;
import com.marineindustryproj.repository.MediaAwarenessReportRepository;
import com.marineindustryproj.service.dto.MediaAwarenessReportDTO;
import com.marineindustryproj.service.mapper.MediaAwarenessReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MediaAwarenessReport.
 */
@Service
@Transactional
public class MediaAwarenessReportServiceImpl implements MediaAwarenessReportService {

    private final Logger log = LoggerFactory.getLogger(MediaAwarenessReportServiceImpl.class);

    private final MediaAwarenessReportRepository mediaAwarenessReportRepository;

    private final MediaAwarenessReportMapper mediaAwarenessReportMapper;

    public MediaAwarenessReportServiceImpl(MediaAwarenessReportRepository mediaAwarenessReportRepository, MediaAwarenessReportMapper mediaAwarenessReportMapper) {
        this.mediaAwarenessReportRepository = mediaAwarenessReportRepository;
        this.mediaAwarenessReportMapper = mediaAwarenessReportMapper;
    }

    /**
     * Save a mediaAwarenessReport.
     *
     * @param mediaAwarenessReportDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MediaAwarenessReportDTO save(MediaAwarenessReportDTO mediaAwarenessReportDTO) {
        log.debug("Request to save MediaAwarenessReport : {}", mediaAwarenessReportDTO);

        MediaAwarenessReport mediaAwarenessReport = mediaAwarenessReportMapper.toEntity(mediaAwarenessReportDTO);
        mediaAwarenessReport = mediaAwarenessReportRepository.save(mediaAwarenessReport);
        return mediaAwarenessReportMapper.toDto(mediaAwarenessReport);
    }

    /**
     * Get all the mediaAwarenessReports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MediaAwarenessReportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MediaAwarenessReports");
        return mediaAwarenessReportRepository.findAll(pageable)
            .map(mediaAwarenessReportMapper::toDto);
    }

    /**
     * Get all the MediaAwarenessReport with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<MediaAwarenessReportDTO> findAllWithEagerRelationships(Pageable pageable) {
        return mediaAwarenessReportRepository.findAllWithEagerRelationships(pageable).map(mediaAwarenessReportMapper::toDto);
    }
    

    /**
     * Get one mediaAwarenessReport by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MediaAwarenessReportDTO> findOne(Long id) {
        log.debug("Request to get MediaAwarenessReport : {}", id);
        return mediaAwarenessReportRepository.findOneWithEagerRelationships(id)
            .map(mediaAwarenessReportMapper::toDto);
    }

    /**
     * Delete the mediaAwarenessReport by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MediaAwarenessReport : {}", id);
        mediaAwarenessReportRepository.deleteById(id);
    }
}
