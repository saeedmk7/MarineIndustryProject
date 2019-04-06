package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.PollService;
import com.marineindustryproj.domain.Poll;
import com.marineindustryproj.repository.PollRepository;
import com.marineindustryproj.service.dto.PollDTO;
import com.marineindustryproj.service.mapper.PollMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Poll.
 */
@Service
@Transactional
public class PollServiceImpl implements PollService {

    private final Logger log = LoggerFactory.getLogger(PollServiceImpl.class);

    private final PollRepository pollRepository;

    private final PollMapper pollMapper;

    public PollServiceImpl(PollRepository pollRepository, PollMapper pollMapper) {
        this.pollRepository = pollRepository;
        this.pollMapper = pollMapper;
    }

    /**
     * Save a poll.
     *
     * @param pollDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PollDTO save(PollDTO pollDTO) {
        log.debug("Request to save Poll : {}", pollDTO);

        Poll poll = pollMapper.toEntity(pollDTO);
        poll = pollRepository.save(poll);
        return pollMapper.toDto(poll);
    }

    /**
     * Get all the polls.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PollDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Polls");
        return pollRepository.findAll(pageable)
            .map(pollMapper::toDto);
    }


    /**
     * Get one poll by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PollDTO> findOne(Long id) {
        log.debug("Request to get Poll : {}", id);
        return pollRepository.findById(id)
            .map(pollMapper::toDto);
    }

    /**
     * Delete the poll by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Poll : {}", id);
        pollRepository.deleteById(id);
    }
}
