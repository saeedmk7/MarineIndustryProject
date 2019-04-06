package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.RadehService;
import com.marineindustryproj.domain.Radeh;
import com.marineindustryproj.repository.RadehRepository;
import com.marineindustryproj.service.dto.RadehDTO;
import com.marineindustryproj.service.mapper.RadehMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Radeh.
 */
@Service
@Transactional
public class RadehServiceImpl implements RadehService {

    private final Logger log = LoggerFactory.getLogger(RadehServiceImpl.class);

    private final RadehRepository radehRepository;

    private final RadehMapper radehMapper;

    public RadehServiceImpl(RadehRepository radehRepository, RadehMapper radehMapper) {
        this.radehRepository = radehRepository;
        this.radehMapper = radehMapper;
    }

    /**
     * Save a radeh.
     *
     * @param radehDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RadehDTO save(RadehDTO radehDTO) {
        log.debug("Request to save Radeh : {}", radehDTO);

        Radeh radeh = radehMapper.toEntity(radehDTO);
        radeh = radehRepository.save(radeh);
        return radehMapper.toDto(radeh);
    }

    /**
     * Get all the radehs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RadehDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Radehs");
        return radehRepository.findAll(pageable)
            .map(radehMapper::toDto);
    }


    /**
     * Get one radeh by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RadehDTO> findOne(Long id) {
        log.debug("Request to get Radeh : {}", id);
        return radehRepository.findById(id)
            .map(radehMapper::toDto);
    }

    /**
     * Delete the radeh by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Radeh : {}", id);
        radehRepository.deleteById(id);
    }
}
