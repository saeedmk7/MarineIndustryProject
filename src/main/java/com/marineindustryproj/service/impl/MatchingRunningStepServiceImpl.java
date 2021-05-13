package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.MatchingRunningStepService;
import com.marineindustryproj.domain.MatchingRunningStep;
import com.marineindustryproj.repository.MatchingRunningStepRepository;
import com.marineindustryproj.service.dto.MatchingRunningStepDTO;
import com.marineindustryproj.service.mapper.MatchingRunningStepMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MatchingRunningStep.
 */
@Service
@Transactional
public class MatchingRunningStepServiceImpl implements MatchingRunningStepService {

    private final Logger log = LoggerFactory.getLogger(MatchingRunningStepServiceImpl.class);

    private final MatchingRunningStepRepository matchingRunningStepRepository;

    private final MatchingRunningStepMapper matchingRunningStepMapper;

    public MatchingRunningStepServiceImpl(MatchingRunningStepRepository matchingRunningStepRepository, MatchingRunningStepMapper matchingRunningStepMapper) {
        this.matchingRunningStepRepository = matchingRunningStepRepository;
        this.matchingRunningStepMapper = matchingRunningStepMapper;
    }

    /**
     * Save a matchingRunningStep.
     *
     * @param matchingRunningStepDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MatchingRunningStepDTO save(MatchingRunningStepDTO matchingRunningStepDTO) {
        log.debug("Request to save MatchingRunningStep : {}", matchingRunningStepDTO);

        MatchingRunningStep matchingRunningStep = matchingRunningStepMapper.toEntity(matchingRunningStepDTO);
        matchingRunningStep = matchingRunningStepRepository.save(matchingRunningStep);
        return matchingRunningStepMapper.toDto(matchingRunningStep);
    }

    /**
     * Get all the matchingRunningSteps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MatchingRunningStepDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MatchingRunningSteps");
        return matchingRunningStepRepository.findAll(pageable)
            .map(matchingRunningStepMapper::toDto);
    }


    /**
     * Get one matchingRunningStep by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MatchingRunningStepDTO> findOne(Long id) {
        log.debug("Request to get MatchingRunningStep : {}", id);
        return matchingRunningStepRepository.findById(id)
            .map(matchingRunningStepMapper::toDto);
    }

    /**
     * Delete the matchingRunningStep by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MatchingRunningStep : {}", id);
        matchingRunningStepRepository.deleteById(id);
    }
}
