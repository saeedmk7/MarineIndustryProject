package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.CompetencyService;
import com.marineindustryproj.domain.Competency;
import com.marineindustryproj.repository.CompetencyRepository;
import com.marineindustryproj.service.dto.CompetencyDTO;
import com.marineindustryproj.service.mapper.CompetencyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Competency.
 */
@Service
@Transactional
public class CompetencyServiceImpl implements CompetencyService {

    private final Logger log = LoggerFactory.getLogger(CompetencyServiceImpl.class);

    private final CompetencyRepository competencyRepository;

    private final CompetencyMapper competencyMapper;

    public CompetencyServiceImpl(CompetencyRepository competencyRepository, CompetencyMapper competencyMapper) {
        this.competencyRepository = competencyRepository;
        this.competencyMapper = competencyMapper;
    }

    /**
     * Save a competency.
     *
     * @param competencyDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CompetencyDTO save(CompetencyDTO competencyDTO) {
        log.debug("Request to save Competency : {}", competencyDTO);

        Competency competency = competencyMapper.toEntity(competencyDTO);
        competency = competencyRepository.save(competency);
        return competencyMapper.toDto(competency);
    }

    /**
     * Get all the competencies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CompetencyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Competencies");
        return competencyRepository.findAll(pageable)
            .map(competencyMapper::toDto);
    }


    /**
     * Get one competency by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CompetencyDTO> findOne(Long id) {
        log.debug("Request to get Competency : {}", id);
        return competencyRepository.findById(id)
            .map(competencyMapper::toDto);
    }

    /**
     * Delete the competency by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Competency : {}", id);
        competencyRepository.deleteById(id);
    }
}
