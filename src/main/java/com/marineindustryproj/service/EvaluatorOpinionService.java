package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EvaluatorOpinionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EvaluatorOpinion.
 */
public interface EvaluatorOpinionService {

    /**
     * Save a evaluatorOpinion.
     *
     * @param evaluatorOpinionDTO the entity to save
     * @return the persisted entity
     */
    EvaluatorOpinionDTO save(EvaluatorOpinionDTO evaluatorOpinionDTO);

    /**
     * Get all the evaluatorOpinions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EvaluatorOpinionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" evaluatorOpinion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EvaluatorOpinionDTO> findOne(Long id);

    /**
     * Delete the "id" evaluatorOpinion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
