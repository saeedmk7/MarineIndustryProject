package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.AcademicRankDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AcademicRank.
 */
public interface AcademicRankService {

    /**
     * Save a academicRank.
     *
     * @param academicRankDTO the entity to save
     * @return the persisted entity
     */
    AcademicRankDTO save(AcademicRankDTO academicRankDTO);

    /**
     * Get all the academicRanks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AcademicRankDTO> findAll(Pageable pageable);


    /**
     * Get the "id" academicRank.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AcademicRankDTO> findOne(Long id);

    /**
     * Delete the "id" academicRank.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
