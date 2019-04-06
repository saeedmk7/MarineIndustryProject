package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.TeachApproachService;
import com.marineindustryproj.domain.TeachApproach;
import com.marineindustryproj.repository.TeachApproachRepository;
import com.marineindustryproj.service.dto.TeachApproachDTO;
import com.marineindustryproj.service.mapper.TeachApproachMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TeachApproach.
 */
@Service
@Transactional
public class TeachApproachServiceImpl implements TeachApproachService {

    private final Logger log = LoggerFactory.getLogger(TeachApproachServiceImpl.class);

    private final TeachApproachRepository teachApproachRepository;

    private final TeachApproachMapper teachApproachMapper;

    public TeachApproachServiceImpl(TeachApproachRepository teachApproachRepository, TeachApproachMapper teachApproachMapper) {
        this.teachApproachRepository = teachApproachRepository;
        this.teachApproachMapper = teachApproachMapper;
    }

    /**
     * Save a teachApproach.
     *
     * @param teachApproachDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TeachApproachDTO save(TeachApproachDTO teachApproachDTO) {
        log.debug("Request to save TeachApproach : {}", teachApproachDTO);

        TeachApproach teachApproach = teachApproachMapper.toEntity(teachApproachDTO);
        teachApproach = teachApproachRepository.save(teachApproach);
        return teachApproachMapper.toDto(teachApproach);
    }

    /**
     * Get all the teachApproaches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TeachApproachDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TeachApproaches");
        return teachApproachRepository.findAll(pageable)
            .map(teachApproachMapper::toDto);
    }


    /**
     * Get one teachApproach by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TeachApproachDTO> findOne(Long id) {
        log.debug("Request to get TeachApproach : {}", id);
        return teachApproachRepository.findById(id)
            .map(teachApproachMapper::toDto);
    }

    /**
     * Delete the teachApproach by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TeachApproach : {}", id);
        teachApproachRepository.deleteById(id);
    }
}
