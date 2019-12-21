package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.MediaAwarenessReportDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MediaAwarenessReport.
 */
public interface MediaAwarenessReportService {

    /**
     * Save a mediaAwarenessReport.
     *
     * @param mediaAwarenessReportDTO the entity to save
     * @return the persisted entity
     */
    MediaAwarenessReportDTO save(MediaAwarenessReportDTO mediaAwarenessReportDTO);

    /**
     * Get all the mediaAwarenessReports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MediaAwarenessReportDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mediaAwarenessReport.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MediaAwarenessReportDTO> findOne(Long id);

    /**
     * Delete the "id" mediaAwarenessReport.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
