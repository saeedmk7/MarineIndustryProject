package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EvaluatorOpinionService;
import com.marineindustryproj.domain.EvaluatorOpinion;
import com.marineindustryproj.repository.EvaluatorOpinionRepository;
import com.marineindustryproj.service.dto.EvaluatorOpinionDTO;
import com.marineindustryproj.service.mapper.EvaluatorOpinionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EvaluatorOpinion.
 */
@Service
@Transactional
public class EvaluatorOpinionServiceImpl implements EvaluatorOpinionService {

    private final Logger log = LoggerFactory.getLogger(EvaluatorOpinionServiceImpl.class);

    private final EvaluatorOpinionRepository evaluatorOpinionRepository;

    private final EvaluatorOpinionMapper evaluatorOpinionMapper;

    public EvaluatorOpinionServiceImpl(EvaluatorOpinionRepository evaluatorOpinionRepository, EvaluatorOpinionMapper evaluatorOpinionMapper) {
        this.evaluatorOpinionRepository = evaluatorOpinionRepository;
        this.evaluatorOpinionMapper = evaluatorOpinionMapper;
    }

    /**
     * Save a evaluatorOpinion.
     *
     * @param evaluatorOpinionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EvaluatorOpinionDTO save(EvaluatorOpinionDTO evaluatorOpinionDTO) {
        log.debug("Request to save EvaluatorOpinion : {}", evaluatorOpinionDTO);

        EvaluatorOpinion evaluatorOpinion = evaluatorOpinionMapper.toEntity(evaluatorOpinionDTO);
        evaluatorOpinion = evaluatorOpinionRepository.save(evaluatorOpinion);
        return evaluatorOpinionMapper.toDto(evaluatorOpinion);
    }

    /**
     * Get all the evaluatorOpinions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EvaluatorOpinionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EvaluatorOpinions");
        return evaluatorOpinionRepository.findAll(pageable)
            .map(evaluatorOpinionMapper::toDto);
    }


    /**
     * Get one evaluatorOpinion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EvaluatorOpinionDTO> findOne(Long id) {
        log.debug("Request to get EvaluatorOpinion : {}", id);
        return evaluatorOpinionRepository.findById(id)
            .map(evaluatorOpinionMapper::toDto);
    }

    /**
     * Delete the evaluatorOpinion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EvaluatorOpinion : {}", id);
        evaluatorOpinionRepository.deleteById(id);
    }
}
