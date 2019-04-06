package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.RasteService;
import com.marineindustryproj.domain.Raste;
import com.marineindustryproj.repository.RasteRepository;
import com.marineindustryproj.service.dto.RasteDTO;
import com.marineindustryproj.service.mapper.RasteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Raste.
 */
@Service
@Transactional
public class RasteServiceImpl implements RasteService {

    private final Logger log = LoggerFactory.getLogger(RasteServiceImpl.class);

    private final RasteRepository rasteRepository;

    private final RasteMapper rasteMapper;

    public RasteServiceImpl(RasteRepository rasteRepository, RasteMapper rasteMapper) {
        this.rasteRepository = rasteRepository;
        this.rasteMapper = rasteMapper;
    }

    /**
     * Save a raste.
     *
     * @param rasteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RasteDTO save(RasteDTO rasteDTO) {
        log.debug("Request to save Raste : {}", rasteDTO);

        Raste raste = rasteMapper.toEntity(rasteDTO);
        raste = rasteRepository.save(raste);
        return rasteMapper.toDto(raste);
    }

    /**
     * Get all the rastes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RasteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Rastes");
        return rasteRepository.findAll(pageable)
            .map(rasteMapper::toDto);
    }


    /**
     * Get one raste by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RasteDTO> findOne(Long id) {
        log.debug("Request to get Raste : {}", id);
        return rasteRepository.findById(id)
            .map(rasteMapper::toDto);
    }

    /**
     * Delete the raste by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Raste : {}", id);
        rasteRepository.deleteById(id);
    }
}
