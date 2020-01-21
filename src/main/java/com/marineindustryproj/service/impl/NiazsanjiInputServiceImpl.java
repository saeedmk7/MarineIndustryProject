package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.NiazsanjiInputService;
import com.marineindustryproj.domain.NiazsanjiInput;
import com.marineindustryproj.repository.NiazsanjiInputRepository;
import com.marineindustryproj.service.dto.NiazsanjiInputDTO;
import com.marineindustryproj.service.mapper.NiazsanjiInputMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing NiazsanjiInput.
 */
@Service
@Transactional
public class NiazsanjiInputServiceImpl implements NiazsanjiInputService {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiInputServiceImpl.class);

    private final NiazsanjiInputRepository niazsanjiInputRepository;

    private final NiazsanjiInputMapper niazsanjiInputMapper;

    public NiazsanjiInputServiceImpl(NiazsanjiInputRepository niazsanjiInputRepository, NiazsanjiInputMapper niazsanjiInputMapper) {
        this.niazsanjiInputRepository = niazsanjiInputRepository;
        this.niazsanjiInputMapper = niazsanjiInputMapper;
    }

    /**
     * Save a niazsanjiInput.
     *
     * @param niazsanjiInputDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NiazsanjiInputDTO save(NiazsanjiInputDTO niazsanjiInputDTO) {
        log.debug("Request to save NiazsanjiInput : {}", niazsanjiInputDTO);

        NiazsanjiInput niazsanjiInput = niazsanjiInputMapper.toEntity(niazsanjiInputDTO);
        niazsanjiInput = niazsanjiInputRepository.save(niazsanjiInput);
        return niazsanjiInputMapper.toDto(niazsanjiInput);
    }

    /**
     * Get all the niazsanjiInputs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NiazsanjiInputDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NiazsanjiInputs");
        return niazsanjiInputRepository.findAll(pageable)
            .map(niazsanjiInputMapper::toDto);
    }


    /**
     * Get one niazsanjiInput by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NiazsanjiInputDTO> findOne(Long id) {
        log.debug("Request to get NiazsanjiInput : {}", id);
        return niazsanjiInputRepository.findById(id)
            .map(niazsanjiInputMapper::toDto);
    }

    /**
     * Delete the niazsanjiInput by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NiazsanjiInput : {}", id);
        niazsanjiInputRepository.deleteById(id);
    }
}
