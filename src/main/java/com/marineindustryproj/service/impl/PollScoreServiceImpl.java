package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.PollScoreService;
import com.marineindustryproj.domain.PollScore;
import com.marineindustryproj.repository.PollScoreRepository;
import com.marineindustryproj.service.dto.PollScoreDTO;
import com.marineindustryproj.service.mapper.PollScoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing PollScore.
 */
@Service
@Transactional
public class PollScoreServiceImpl implements PollScoreService {

    private final Logger log = LoggerFactory.getLogger(PollScoreServiceImpl.class);

    private final PollScoreRepository pollScoreRepository;

    private final PollScoreMapper pollScoreMapper;

    public PollScoreServiceImpl(PollScoreRepository pollScoreRepository, PollScoreMapper pollScoreMapper) {
        this.pollScoreRepository = pollScoreRepository;
        this.pollScoreMapper = pollScoreMapper;
    }

    /**
     * Save a pollScore.
     *
     * @param pollScoreDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PollScoreDTO save(PollScoreDTO pollScoreDTO) {
        log.debug("Request to save PollScore : {}", pollScoreDTO);

        PollScore pollScore = pollScoreMapper.toEntity(pollScoreDTO);
        pollScore = pollScoreRepository.save(pollScore);
        return pollScoreMapper.toDto(pollScore);
    }

    /**
     * Get all the pollScores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PollScoreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PollScores");
        return pollScoreRepository.findAll(pageable)
            .map(pollScoreMapper::toDto);
    }


    /**
     * Get one pollScore by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PollScoreDTO> findOne(Long id) {
        log.debug("Request to get PollScore : {}", id);
        return pollScoreRepository.findById(id)
            .map(pollScoreMapper::toDto);
    }

    /**
     * Delete the pollScore by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PollScore : {}", id);
        pollScoreRepository.deleteById(id);
    }
}
