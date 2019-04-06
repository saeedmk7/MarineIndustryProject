package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EvaluationMethodService;
import com.marineindustryproj.domain.EvaluationMethod;
import com.marineindustryproj.repository.EvaluationMethodRepository;
import com.marineindustryproj.service.dto.EvaluationMethodDTO;
import com.marineindustryproj.service.mapper.EvaluationMethodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EvaluationMethod.
 */
@Service
@Transactional
public class EvaluationMethodServiceImpl implements EvaluationMethodService {

    private final Logger log = LoggerFactory.getLogger(EvaluationMethodServiceImpl.class);

    private final EvaluationMethodRepository evaluationMethodRepository;

    private final EvaluationMethodMapper evaluationMethodMapper;

    public EvaluationMethodServiceImpl(EvaluationMethodRepository evaluationMethodRepository, EvaluationMethodMapper evaluationMethodMapper) {
        this.evaluationMethodRepository = evaluationMethodRepository;
        this.evaluationMethodMapper = evaluationMethodMapper;
    }

    /**
     * Save a evaluationMethod.
     *
     * @param evaluationMethodDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EvaluationMethodDTO save(EvaluationMethodDTO evaluationMethodDTO) {
        log.debug("Request to save EvaluationMethod : {}", evaluationMethodDTO);

        EvaluationMethod evaluationMethod = evaluationMethodMapper.toEntity(evaluationMethodDTO);
        evaluationMethod = evaluationMethodRepository.save(evaluationMethod);
        return evaluationMethodMapper.toDto(evaluationMethod);
    }

    /**
     * Get all the evaluationMethods.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EvaluationMethodDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EvaluationMethods");
        return evaluationMethodRepository.findAll(pageable)
            .map(evaluationMethodMapper::toDto);
    }


    /**
     * Get one evaluationMethod by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EvaluationMethodDTO> findOne(Long id) {
        log.debug("Request to get EvaluationMethod : {}", id);
        return evaluationMethodRepository.findById(id)
            .map(evaluationMethodMapper::toDto);
    }

    /**
     * Delete the evaluationMethod by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EvaluationMethod : {}", id);
        evaluationMethodRepository.deleteById(id);
    }
}
