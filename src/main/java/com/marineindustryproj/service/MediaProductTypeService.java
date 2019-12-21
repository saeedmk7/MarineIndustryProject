package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.MediaProductTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MediaProductType.
 */
public interface MediaProductTypeService {

    /**
     * Save a mediaProductType.
     *
     * @param mediaProductTypeDTO the entity to save
     * @return the persisted entity
     */
    MediaProductTypeDTO save(MediaProductTypeDTO mediaProductTypeDTO);

    /**
     * Get all the mediaProductTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MediaProductTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mediaProductType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MediaProductTypeDTO> findOne(Long id);

    /**
     * Delete the "id" mediaProductType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
