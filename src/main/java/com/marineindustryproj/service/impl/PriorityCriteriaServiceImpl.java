package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.PriorityCriteriaService;
import com.marineindustryproj.domain.PriorityCriteria;
import com.marineindustryproj.repository.PriorityCriteriaRepository;
import com.marineindustryproj.service.dto.PriorityCriteriaDTO;
import com.marineindustryproj.service.mapper.PriorityCriteriaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing PriorityCriteria.
 */
@Service
@Transactional
public class PriorityCriteriaServiceImpl implements PriorityCriteriaService {

    private final Logger log = LoggerFactory.getLogger(PriorityCriteriaServiceImpl.class);

    private final PriorityCriteriaRepository priorityCriteriaRepository;

    private final PriorityCriteriaMapper priorityCriteriaMapper;

    public PriorityCriteriaServiceImpl(PriorityCriteriaRepository priorityCriteriaRepository, PriorityCriteriaMapper priorityCriteriaMapper) {
        this.priorityCriteriaRepository = priorityCriteriaRepository;
        this.priorityCriteriaMapper = priorityCriteriaMapper;
    }

    /**
     * Save a priorityCriteria.
     *
     * @param priorityCriteriaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PriorityCriteriaDTO save(PriorityCriteriaDTO priorityCriteriaDTO) {
        log.debug("Request to save PriorityCriteria : {}", priorityCriteriaDTO);

        PriorityCriteria priorityCriteria = priorityCriteriaMapper.toEntity(priorityCriteriaDTO);
        priorityCriteria = priorityCriteriaRepository.save(priorityCriteria);
        return priorityCriteriaMapper.toDto(priorityCriteria);
    }

    /**
     * Get all the priorityCriteria.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PriorityCriteriaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PriorityCriteria");
        return priorityCriteriaRepository.findAll(pageable)
            .map(priorityCriteriaMapper::toDto);
    }


    /**
     * Get one priorityCriteria by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PriorityCriteriaDTO> findOne(Long id) {
        log.debug("Request to get PriorityCriteria : {}", id);
        return priorityCriteriaRepository.findById(id)
            .map(priorityCriteriaMapper::toDto);
    }

    /**
     * Delete the priorityCriteria by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PriorityCriteria : {}", id);
        priorityCriteriaRepository.deleteById(id);
    }
}
