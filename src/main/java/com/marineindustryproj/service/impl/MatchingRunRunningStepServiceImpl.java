package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.MatchingRunRunningStepService;
import com.marineindustryproj.domain.MatchingRunRunningStep;
import com.marineindustryproj.repository.MatchingRunRunningStepRepository;
import com.marineindustryproj.service.dto.MatchingRunRunningStepDTO;
import com.marineindustryproj.service.mapper.MatchingRunRunningStepMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MatchingRunRunningStep.
 */
@Service
@Transactional
public class MatchingRunRunningStepServiceImpl implements MatchingRunRunningStepService {

    private final Logger log = LoggerFactory.getLogger(MatchingRunRunningStepServiceImpl.class);

    private final MatchingRunRunningStepRepository matchingRunRunningStepRepository;

    private final MatchingRunRunningStepMapper matchingRunRunningStepMapper;

    public MatchingRunRunningStepServiceImpl(MatchingRunRunningStepRepository matchingRunRunningStepRepository, MatchingRunRunningStepMapper matchingRunRunningStepMapper) {
        this.matchingRunRunningStepRepository = matchingRunRunningStepRepository;
        this.matchingRunRunningStepMapper = matchingRunRunningStepMapper;
    }

    /**
     * Save a matchingRunRunningStep.
     *
     * @param matchingRunRunningStepDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MatchingRunRunningStepDTO save(MatchingRunRunningStepDTO matchingRunRunningStepDTO) {
        log.debug("Request to save MatchingRunRunningStep : {}", matchingRunRunningStepDTO);

        MatchingRunRunningStep matchingRunRunningStep = matchingRunRunningStepMapper.toEntity(matchingRunRunningStepDTO);
        matchingRunRunningStep = matchingRunRunningStepRepository.save(matchingRunRunningStep);
        return matchingRunRunningStepMapper.toDto(matchingRunRunningStep);
    }

    /**
     * Get all the matchingRunRunningSteps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MatchingRunRunningStepDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MatchingRunRunningSteps");
        return matchingRunRunningStepRepository.findAll(pageable)
            .map(matchingRunRunningStepMapper::toDto);
    }


    /**
     * Get one matchingRunRunningStep by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MatchingRunRunningStepDTO> findOne(Long id) {
        log.debug("Request to get MatchingRunRunningStep : {}", id);
        return matchingRunRunningStepRepository.findById(id)
            .map(matchingRunRunningStepMapper::toDto);
    }

    /**
     * Delete the matchingRunRunningStep by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MatchingRunRunningStep : {}", id);
        matchingRunRunningStepRepository.deleteById(id);
    }
}
