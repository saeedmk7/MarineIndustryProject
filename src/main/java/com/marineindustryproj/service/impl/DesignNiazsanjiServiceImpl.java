package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.DesignNiazsanjiService;
import com.marineindustryproj.domain.DesignNiazsanji;
import com.marineindustryproj.repository.DesignNiazsanjiRepository;
import com.marineindustryproj.service.dto.DesignNiazsanjiDTO;
import com.marineindustryproj.service.mapper.DesignNiazsanjiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing DesignNiazsanji.
 */
@Service
@Transactional
public class DesignNiazsanjiServiceImpl implements DesignNiazsanjiService {

    private final Logger log = LoggerFactory.getLogger(DesignNiazsanjiServiceImpl.class);

    private final DesignNiazsanjiRepository designNiazsanjiRepository;

    private final DesignNiazsanjiMapper designNiazsanjiMapper;

    public DesignNiazsanjiServiceImpl(DesignNiazsanjiRepository designNiazsanjiRepository, DesignNiazsanjiMapper designNiazsanjiMapper) {
        this.designNiazsanjiRepository = designNiazsanjiRepository;
        this.designNiazsanjiMapper = designNiazsanjiMapper;
    }

    /**
     * Save a designNiazsanji.
     *
     * @param designNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DesignNiazsanjiDTO save(DesignNiazsanjiDTO designNiazsanjiDTO) {
        log.debug("Request to save DesignNiazsanji : {}", designNiazsanjiDTO);

        DesignNiazsanji designNiazsanji = designNiazsanjiMapper.toEntity(designNiazsanjiDTO);
        designNiazsanji = designNiazsanjiRepository.save(designNiazsanji);
        return designNiazsanjiMapper.toDto(designNiazsanji);
    }

    /**
     * Get all the designNiazsanjis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DesignNiazsanjiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DesignNiazsanjis");
        return designNiazsanjiRepository.findAll(pageable)
            .map(designNiazsanjiMapper::toDto);
    }

    /**
     * Get all the DesignNiazsanji with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<DesignNiazsanjiDTO> findAllWithEagerRelationships(Pageable pageable) {
        return designNiazsanjiRepository.findAllWithEagerRelationships(pageable).map(designNiazsanjiMapper::toDto);
    }
    

    /**
     * Get one designNiazsanji by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DesignNiazsanjiDTO> findOne(Long id) {
        log.debug("Request to get DesignNiazsanji : {}", id);
        return designNiazsanjiRepository.findOneWithEagerRelationships(id)
            .map(designNiazsanjiMapper::toDto);
    }

    /**
     * Delete the designNiazsanji by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DesignNiazsanji : {}", id);
        designNiazsanjiRepository.deleteById(id);
    }
}
