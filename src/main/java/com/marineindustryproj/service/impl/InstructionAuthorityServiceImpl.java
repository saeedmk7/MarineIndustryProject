package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.InstructionAuthorityService;
import com.marineindustryproj.domain.InstructionAuthority;
import com.marineindustryproj.repository.InstructionAuthorityRepository;
import com.marineindustryproj.service.dto.InstructionAuthorityDTO;
import com.marineindustryproj.service.mapper.InstructionAuthorityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing InstructionAuthority.
 */
@Service
@Transactional
public class InstructionAuthorityServiceImpl implements InstructionAuthorityService {

    private final Logger log = LoggerFactory.getLogger(InstructionAuthorityServiceImpl.class);

    private final InstructionAuthorityRepository instructionAuthorityRepository;

    private final InstructionAuthorityMapper instructionAuthorityMapper;

    public InstructionAuthorityServiceImpl(InstructionAuthorityRepository instructionAuthorityRepository, InstructionAuthorityMapper instructionAuthorityMapper) {
        this.instructionAuthorityRepository = instructionAuthorityRepository;
        this.instructionAuthorityMapper = instructionAuthorityMapper;
    }

    /**
     * Save a instructionAuthority.
     *
     * @param instructionAuthorityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public InstructionAuthorityDTO save(InstructionAuthorityDTO instructionAuthorityDTO) {
        log.debug("Request to save InstructionAuthority : {}", instructionAuthorityDTO);

        InstructionAuthority instructionAuthority = instructionAuthorityMapper.toEntity(instructionAuthorityDTO);
        instructionAuthority = instructionAuthorityRepository.save(instructionAuthority);
        return instructionAuthorityMapper.toDto(instructionAuthority);
    }

    /**
     * Get all the instructionAuthorities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InstructionAuthorityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InstructionAuthorities");
        return instructionAuthorityRepository.findAll(pageable)
            .map(instructionAuthorityMapper::toDto);
    }


    /**
     * Get one instructionAuthority by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InstructionAuthorityDTO> findOne(Long id) {
        log.debug("Request to get InstructionAuthority : {}", id);
        return instructionAuthorityRepository.findById(id)
            .map(instructionAuthorityMapper::toDto);
    }

    /**
     * Delete the instructionAuthority by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InstructionAuthority : {}", id);
        instructionAuthorityRepository.deleteById(id);
    }
}
