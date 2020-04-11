package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.NiazsanjiPersonCriteriaService;
import com.marineindustryproj.domain.NiazsanjiPersonCriteria;
import com.marineindustryproj.repository.NiazsanjiPersonCriteriaRepository;
import com.marineindustryproj.service.dto.NiazsanjiPersonCriteriaDTO;
import com.marineindustryproj.service.mapper.NiazsanjiPersonCriteriaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing NiazsanjiPersonCriteria.
 */
@Service
@Transactional
public class NiazsanjiPersonCriteriaServiceImpl implements NiazsanjiPersonCriteriaService {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiPersonCriteriaServiceImpl.class);

    private final NiazsanjiPersonCriteriaRepository niazsanjiPersonCriteriaRepository;

    private final NiazsanjiPersonCriteriaMapper niazsanjiPersonCriteriaMapper;

    public NiazsanjiPersonCriteriaServiceImpl(NiazsanjiPersonCriteriaRepository niazsanjiPersonCriteriaRepository, NiazsanjiPersonCriteriaMapper niazsanjiPersonCriteriaMapper) {
        this.niazsanjiPersonCriteriaRepository = niazsanjiPersonCriteriaRepository;
        this.niazsanjiPersonCriteriaMapper = niazsanjiPersonCriteriaMapper;
    }

    /**
     * Save a niazsanjiPersonCriteria.
     *
     * @param niazsanjiPersonCriteriaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NiazsanjiPersonCriteriaDTO save(NiazsanjiPersonCriteriaDTO niazsanjiPersonCriteriaDTO) {
        log.debug("Request to save NiazsanjiPersonCriteria : {}", niazsanjiPersonCriteriaDTO);

        NiazsanjiPersonCriteria niazsanjiPersonCriteria = niazsanjiPersonCriteriaMapper.toEntity(niazsanjiPersonCriteriaDTO);
        niazsanjiPersonCriteria = niazsanjiPersonCriteriaRepository.save(niazsanjiPersonCriteria);
        return niazsanjiPersonCriteriaMapper.toDto(niazsanjiPersonCriteria);
    }

    /**
     * Get all the niazsanjiPersonCriteria.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NiazsanjiPersonCriteriaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NiazsanjiPersonCriteria");
        return niazsanjiPersonCriteriaRepository.findAll(pageable)
            .map(niazsanjiPersonCriteriaMapper::toDto);
    }


    /**
     * Get one niazsanjiPersonCriteria by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NiazsanjiPersonCriteriaDTO> findOne(Long id) {
        log.debug("Request to get NiazsanjiPersonCriteria : {}", id);
        return niazsanjiPersonCriteriaRepository.findById(id)
            .map(niazsanjiPersonCriteriaMapper::toDto);
    }

    /**
     * Delete the niazsanjiPersonCriteria by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NiazsanjiPersonCriteria : {}", id);
        niazsanjiPersonCriteriaRepository.deleteById(id);
    }
}
