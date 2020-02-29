package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.AssessmentMethodService;
import com.marineindustryproj.domain.AssessmentMethod;
import com.marineindustryproj.repository.AssessmentMethodRepository;
import com.marineindustryproj.service.dto.AssessmentMethodDTO;
import com.marineindustryproj.service.mapper.AssessmentMethodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AssessmentMethod.
 */
@Service
@Transactional
public class AssessmentMethodServiceImpl implements AssessmentMethodService {

    private final Logger log = LoggerFactory.getLogger(AssessmentMethodServiceImpl.class);

    private final AssessmentMethodRepository assessmentMethodRepository;

    private final AssessmentMethodMapper assessmentMethodMapper;

    public AssessmentMethodServiceImpl(AssessmentMethodRepository assessmentMethodRepository, AssessmentMethodMapper assessmentMethodMapper) {
        this.assessmentMethodRepository = assessmentMethodRepository;
        this.assessmentMethodMapper = assessmentMethodMapper;
    }

    /**
     * Save a assessmentMethod.
     *
     * @param assessmentMethodDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AssessmentMethodDTO save(AssessmentMethodDTO assessmentMethodDTO) {
        log.debug("Request to save AssessmentMethod : {}", assessmentMethodDTO);

        AssessmentMethod assessmentMethod = assessmentMethodMapper.toEntity(assessmentMethodDTO);
        assessmentMethod = assessmentMethodRepository.save(assessmentMethod);
        return assessmentMethodMapper.toDto(assessmentMethod);
    }

    /**
     * Get all the assessmentMethods.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AssessmentMethodDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AssessmentMethods");
        return assessmentMethodRepository.findAll(pageable)
            .map(assessmentMethodMapper::toDto);
    }


    /**
     * Get one assessmentMethod by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AssessmentMethodDTO> findOne(Long id) {
        log.debug("Request to get AssessmentMethod : {}", id);
        return assessmentMethodRepository.findById(id)
            .map(assessmentMethodMapper::toDto);
    }

    /**
     * Delete the assessmentMethod by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AssessmentMethod : {}", id);
        assessmentMethodRepository.deleteById(id);
    }
}
