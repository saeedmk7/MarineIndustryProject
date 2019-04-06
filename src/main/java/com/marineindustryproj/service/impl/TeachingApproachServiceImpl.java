package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.TeachingApproachService;
import com.marineindustryproj.domain.TeachingApproach;
import com.marineindustryproj.repository.TeachingApproachRepository;
import com.marineindustryproj.service.dto.TeachingApproachDTO;
import com.marineindustryproj.service.mapper.TeachingApproachMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TeachingApproach.
 */
@Service
@Transactional
public class TeachingApproachServiceImpl implements TeachingApproachService {

    private final Logger log = LoggerFactory.getLogger(TeachingApproachServiceImpl.class);

    private final TeachingApproachRepository teachingApproachRepository;

    private final TeachingApproachMapper teachingApproachMapper;

    public TeachingApproachServiceImpl(TeachingApproachRepository teachingApproachRepository, TeachingApproachMapper teachingApproachMapper) {
        this.teachingApproachRepository = teachingApproachRepository;
        this.teachingApproachMapper = teachingApproachMapper;
    }

    /**
     * Save a teachingApproach.
     *
     * @param teachingApproachDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TeachingApproachDTO save(TeachingApproachDTO teachingApproachDTO) {
        log.debug("Request to save TeachingApproach : {}", teachingApproachDTO);

        TeachingApproach teachingApproach = teachingApproachMapper.toEntity(teachingApproachDTO);
        teachingApproach = teachingApproachRepository.save(teachingApproach);
        return teachingApproachMapper.toDto(teachingApproach);
    }

    /**
     * Get all the teachingApproaches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TeachingApproachDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TeachingApproaches");
        return teachingApproachRepository.findAll(pageable)
            .map(teachingApproachMapper::toDto);
    }


    /**
     * Get one teachingApproach by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TeachingApproachDTO> findOne(Long id) {
        log.debug("Request to get TeachingApproach : {}", id);
        return teachingApproachRepository.findById(id)
            .map(teachingApproachMapper::toDto);
    }

    /**
     * Delete the teachingApproach by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TeachingApproach : {}", id);
        teachingApproachRepository.deleteById(id);
    }
}
