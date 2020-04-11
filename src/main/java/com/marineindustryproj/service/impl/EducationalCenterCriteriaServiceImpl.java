package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EducationalCenterCriteriaService;
import com.marineindustryproj.domain.EducationalCenterCriteria;
import com.marineindustryproj.repository.EducationalCenterCriteriaRepository;
import com.marineindustryproj.service.dto.EducationalCenterCriteriaDTO;
import com.marineindustryproj.service.mapper.EducationalCenterCriteriaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EducationalCenterCriteria.
 */
@Service
@Transactional
public class EducationalCenterCriteriaServiceImpl implements EducationalCenterCriteriaService {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterCriteriaServiceImpl.class);

    private final EducationalCenterCriteriaRepository educationalCenterCriteriaRepository;

    private final EducationalCenterCriteriaMapper educationalCenterCriteriaMapper;

    public EducationalCenterCriteriaServiceImpl(EducationalCenterCriteriaRepository educationalCenterCriteriaRepository, EducationalCenterCriteriaMapper educationalCenterCriteriaMapper) {
        this.educationalCenterCriteriaRepository = educationalCenterCriteriaRepository;
        this.educationalCenterCriteriaMapper = educationalCenterCriteriaMapper;
    }

    /**
     * Save a educationalCenterCriteria.
     *
     * @param educationalCenterCriteriaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EducationalCenterCriteriaDTO save(EducationalCenterCriteriaDTO educationalCenterCriteriaDTO) {
        log.debug("Request to save EducationalCenterCriteria : {}", educationalCenterCriteriaDTO);

        EducationalCenterCriteria educationalCenterCriteria = educationalCenterCriteriaMapper.toEntity(educationalCenterCriteriaDTO);
        educationalCenterCriteria = educationalCenterCriteriaRepository.save(educationalCenterCriteria);
        return educationalCenterCriteriaMapper.toDto(educationalCenterCriteria);
    }

    /**
     * Get all the educationalCenterCriteria.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EducationalCenterCriteriaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EducationalCenterCriteria");
        return educationalCenterCriteriaRepository.findAll(pageable)
            .map(educationalCenterCriteriaMapper::toDto);
    }


    /**
     * Get one educationalCenterCriteria by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EducationalCenterCriteriaDTO> findOne(Long id) {
        log.debug("Request to get EducationalCenterCriteria : {}", id);
        return educationalCenterCriteriaRepository.findById(id)
            .map(educationalCenterCriteriaMapper::toDto);
    }

    /**
     * Delete the educationalCenterCriteria by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EducationalCenterCriteria : {}", id);
        educationalCenterCriteriaRepository.deleteById(id);
    }
}
