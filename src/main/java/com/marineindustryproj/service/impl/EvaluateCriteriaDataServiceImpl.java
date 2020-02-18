package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EvaluateCriteriaDataService;
import com.marineindustryproj.domain.EvaluateCriteriaData;
import com.marineindustryproj.repository.EvaluateCriteriaDataRepository;
import com.marineindustryproj.service.dto.EvaluateCriteriaDataDTO;
import com.marineindustryproj.service.mapper.EvaluateCriteriaDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EvaluateCriteriaData.
 */
@Service
@Transactional
public class EvaluateCriteriaDataServiceImpl implements EvaluateCriteriaDataService {

    private final Logger log = LoggerFactory.getLogger(EvaluateCriteriaDataServiceImpl.class);

    private final EvaluateCriteriaDataRepository evaluateCriteriaDataRepository;

    private final EvaluateCriteriaDataMapper evaluateCriteriaDataMapper;

    public EvaluateCriteriaDataServiceImpl(EvaluateCriteriaDataRepository evaluateCriteriaDataRepository, EvaluateCriteriaDataMapper evaluateCriteriaDataMapper) {
        this.evaluateCriteriaDataRepository = evaluateCriteriaDataRepository;
        this.evaluateCriteriaDataMapper = evaluateCriteriaDataMapper;
    }

    /**
     * Save a evaluateCriteriaData.
     *
     * @param evaluateCriteriaDataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EvaluateCriteriaDataDTO save(EvaluateCriteriaDataDTO evaluateCriteriaDataDTO) {
        log.debug("Request to save EvaluateCriteriaData : {}", evaluateCriteriaDataDTO);

        EvaluateCriteriaData evaluateCriteriaData = evaluateCriteriaDataMapper.toEntity(evaluateCriteriaDataDTO);
        evaluateCriteriaData = evaluateCriteriaDataRepository.save(evaluateCriteriaData);
        return evaluateCriteriaDataMapper.toDto(evaluateCriteriaData);
    }

    /**
     * Get all the evaluateCriteriaData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EvaluateCriteriaDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EvaluateCriteriaData");
        return evaluateCriteriaDataRepository.findAll(pageable)
            .map(evaluateCriteriaDataMapper::toDto);
    }

    /**
     * Get all the EvaluateCriteriaData with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<EvaluateCriteriaDataDTO> findAllWithEagerRelationships(Pageable pageable) {
        return evaluateCriteriaDataRepository.findAllWithEagerRelationships(pageable).map(evaluateCriteriaDataMapper::toDto);
    }
    

    /**
     * Get one evaluateCriteriaData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EvaluateCriteriaDataDTO> findOne(Long id) {
        log.debug("Request to get EvaluateCriteriaData : {}", id);
        return evaluateCriteriaDataRepository.findOneWithEagerRelationships(id)
            .map(evaluateCriteriaDataMapper::toDto);
    }

    /**
     * Delete the evaluateCriteriaData by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EvaluateCriteriaData : {}", id);
        evaluateCriteriaDataRepository.deleteById(id);
    }
}
