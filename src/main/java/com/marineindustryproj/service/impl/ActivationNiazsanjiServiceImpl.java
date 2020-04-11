package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import com.marineindustryproj.service.ActivationNiazsanjiQueryService;
import com.marineindustryproj.service.ActivationNiazsanjiService;
import com.marineindustryproj.domain.ActivationNiazsanji;
import com.marineindustryproj.repository.ActivationNiazsanjiRepository;
import com.marineindustryproj.service.dto.ActivationNiazsanjiCriteria;
import com.marineindustryproj.service.dto.ActivationNiazsanjiDTO;
import com.marineindustryproj.service.mapper.ActivationNiazsanjiMapper;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing ActivationNiazsanji.
 */
@Service
@Transactional
public class ActivationNiazsanjiServiceImpl implements ActivationNiazsanjiService {

    private final Logger log = LoggerFactory.getLogger(ActivationNiazsanjiServiceImpl.class);

    private final ActivationNiazsanjiRepository activationNiazsanjiRepository;

    private final ActivationNiazsanjiMapper activationNiazsanjiMapper;

    private final ActivationNiazsanjiQueryService activationNiazsanjiQueryService;

    public ActivationNiazsanjiServiceImpl(ActivationNiazsanjiRepository activationNiazsanjiRepository, ActivationNiazsanjiMapper activationNiazsanjiMapper, ActivationNiazsanjiQueryService activationNiazsanjiQueryService) {
        this.activationNiazsanjiRepository = activationNiazsanjiRepository;
        this.activationNiazsanjiMapper = activationNiazsanjiMapper;
        this.activationNiazsanjiQueryService = activationNiazsanjiQueryService;
    }

    /**
     * Save a activationNiazsanji.
     *
     * @param activationNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ActivationNiazsanjiDTO save(ActivationNiazsanjiDTO activationNiazsanjiDTO) {
        log.debug("Request to save ActivationNiazsanji : {}", activationNiazsanjiDTO);

        ActivationNiazsanji activationNiazsanji = activationNiazsanjiMapper.toEntity(activationNiazsanjiDTO);
        activationNiazsanji = activationNiazsanjiRepository.save(activationNiazsanji);
        return activationNiazsanjiMapper.toDto(activationNiazsanji);
    }

    /**
     * Get all the activationNiazsanjis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ActivationNiazsanjiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ActivationNiazsanjis");
        return activationNiazsanjiRepository.findAll(pageable)
            .map(activationNiazsanjiMapper::toDto);
    }


    /**
     * Get one activationNiazsanji by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ActivationNiazsanjiDTO> findOne(Long id) {
        log.debug("Request to get ActivationNiazsanji : {}", id);
        return activationNiazsanjiRepository.findById(id)
            .map(activationNiazsanjiMapper::toDto);
    }

    /**
     * Delete the activationNiazsanji by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ActivationNiazsanji : {}", id);
        activationNiazsanjiRepository.deleteById(id);
    }
}
