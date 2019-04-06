package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.CriterionService;
import com.marineindustryproj.domain.Criterion;
import com.marineindustryproj.repository.CriterionRepository;
import com.marineindustryproj.service.dto.CriterionDTO;
import com.marineindustryproj.service.mapper.CriterionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Criterion.
 */
@Service
@Transactional
public class CriterionServiceImpl implements CriterionService {

    private final Logger log = LoggerFactory.getLogger(CriterionServiceImpl.class);

    private final CriterionRepository criterionRepository;

    private final CriterionMapper criterionMapper;

    public CriterionServiceImpl(CriterionRepository criterionRepository, CriterionMapper criterionMapper) {
        this.criterionRepository = criterionRepository;
        this.criterionMapper = criterionMapper;
    }

    /**
     * Save a criterion.
     *
     * @param criterionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CriterionDTO save(CriterionDTO criterionDTO) {
        log.debug("Request to save Criterion : {}", criterionDTO);

        Criterion criterion = criterionMapper.toEntity(criterionDTO);
        criterion = criterionRepository.save(criterion);
        return criterionMapper.toDto(criterion);
    }

    /**
     * Get all the criteria.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CriterionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Criteria");
        return criterionRepository.findAll(pageable)
            .map(criterionMapper::toDto);
    }


    /**
     * Get one criterion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CriterionDTO> findOne(Long id) {
        log.debug("Request to get Criterion : {}", id);
        return criterionRepository.findById(id)
            .map(criterionMapper::toDto);
    }

    /**
     * Delete the criterion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Criterion : {}", id);
        criterionRepository.deleteById(id);
    }
}
