package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.ConditionsOfParticipantService;
import com.marineindustryproj.domain.ConditionsOfParticipant;
import com.marineindustryproj.repository.ConditionsOfParticipantRepository;
import com.marineindustryproj.service.dto.ConditionsOfParticipantDTO;
import com.marineindustryproj.service.mapper.ConditionsOfParticipantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ConditionsOfParticipant.
 */
@Service
@Transactional
public class ConditionsOfParticipantServiceImpl implements ConditionsOfParticipantService {

    private final Logger log = LoggerFactory.getLogger(ConditionsOfParticipantServiceImpl.class);

    private final ConditionsOfParticipantRepository conditionsOfParticipantRepository;

    private final ConditionsOfParticipantMapper conditionsOfParticipantMapper;

    public ConditionsOfParticipantServiceImpl(ConditionsOfParticipantRepository conditionsOfParticipantRepository, ConditionsOfParticipantMapper conditionsOfParticipantMapper) {
        this.conditionsOfParticipantRepository = conditionsOfParticipantRepository;
        this.conditionsOfParticipantMapper = conditionsOfParticipantMapper;
    }

    /**
     * Save a conditionsOfParticipant.
     *
     * @param conditionsOfParticipantDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConditionsOfParticipantDTO save(ConditionsOfParticipantDTO conditionsOfParticipantDTO) {
        log.debug("Request to save ConditionsOfParticipant : {}", conditionsOfParticipantDTO);

        ConditionsOfParticipant conditionsOfParticipant = conditionsOfParticipantMapper.toEntity(conditionsOfParticipantDTO);
        conditionsOfParticipant = conditionsOfParticipantRepository.save(conditionsOfParticipant);
        return conditionsOfParticipantMapper.toDto(conditionsOfParticipant);
    }

    /**
     * Get all the conditionsOfParticipants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConditionsOfParticipantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ConditionsOfParticipants");
        return conditionsOfParticipantRepository.findAll(pageable)
            .map(conditionsOfParticipantMapper::toDto);
    }


    /**
     * Get one conditionsOfParticipant by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ConditionsOfParticipantDTO> findOne(Long id) {
        log.debug("Request to get ConditionsOfParticipant : {}", id);
        return conditionsOfParticipantRepository.findById(id)
            .map(conditionsOfParticipantMapper::toDto);
    }

    /**
     * Delete the conditionsOfParticipant by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConditionsOfParticipant : {}", id);
        conditionsOfParticipantRepository.deleteById(id);
    }
}
