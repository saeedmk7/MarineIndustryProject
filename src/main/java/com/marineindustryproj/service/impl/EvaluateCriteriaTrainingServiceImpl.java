package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EvaluateCriteriaTrainingService;
import com.marineindustryproj.domain.EvaluateCriteriaTraining;
import com.marineindustryproj.repository.EvaluateCriteriaTrainingRepository;
import com.marineindustryproj.service.dto.EvaluateCriteriaTrainingDTO;
import com.marineindustryproj.service.mapper.EvaluateCriteriaTrainingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EvaluateCriteriaTraining.
 */
@Service
@Transactional
public class EvaluateCriteriaTrainingServiceImpl implements EvaluateCriteriaTrainingService {

    private final Logger log = LoggerFactory.getLogger(EvaluateCriteriaTrainingServiceImpl.class);

    private final EvaluateCriteriaTrainingRepository evaluateCriteriaTrainingRepository;

    private final EvaluateCriteriaTrainingMapper evaluateCriteriaTrainingMapper;

    public EvaluateCriteriaTrainingServiceImpl(EvaluateCriteriaTrainingRepository evaluateCriteriaTrainingRepository, EvaluateCriteriaTrainingMapper evaluateCriteriaTrainingMapper) {
        this.evaluateCriteriaTrainingRepository = evaluateCriteriaTrainingRepository;
        this.evaluateCriteriaTrainingMapper = evaluateCriteriaTrainingMapper;
    }

    /**
     * Save a evaluateCriteriaTraining.
     *
     * @param evaluateCriteriaTrainingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EvaluateCriteriaTrainingDTO save(EvaluateCriteriaTrainingDTO evaluateCriteriaTrainingDTO) {
        log.debug("Request to save EvaluateCriteriaTraining : {}", evaluateCriteriaTrainingDTO);

        EvaluateCriteriaTraining evaluateCriteriaTraining = evaluateCriteriaTrainingMapper.toEntity(evaluateCriteriaTrainingDTO);
        evaluateCriteriaTraining = evaluateCriteriaTrainingRepository.save(evaluateCriteriaTraining);
        return evaluateCriteriaTrainingMapper.toDto(evaluateCriteriaTraining);
    }

    /**
     * Get all the evaluateCriteriaTrainings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EvaluateCriteriaTrainingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EvaluateCriteriaTrainings");
        return evaluateCriteriaTrainingRepository.findAll(pageable)
            .map(evaluateCriteriaTrainingMapper::toDto);
    }

    /**
     * Get all the EvaluateCriteriaTraining with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<EvaluateCriteriaTrainingDTO> findAllWithEagerRelationships(Pageable pageable) {
        return evaluateCriteriaTrainingRepository.findAllWithEagerRelationships(pageable).map(evaluateCriteriaTrainingMapper::toDto);
    }
    

    /**
     * Get one evaluateCriteriaTraining by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EvaluateCriteriaTrainingDTO> findOne(Long id) {
        log.debug("Request to get EvaluateCriteriaTraining : {}", id);
        return evaluateCriteriaTrainingRepository.findOneWithEagerRelationships(id)
            .map(evaluateCriteriaTrainingMapper::toDto);
    }

    /**
     * Delete the evaluateCriteriaTraining by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EvaluateCriteriaTraining : {}", id);
        evaluateCriteriaTrainingRepository.deleteById(id);
    }
}
