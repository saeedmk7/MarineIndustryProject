package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.PollItemService;
import com.marineindustryproj.domain.PollItem;
import com.marineindustryproj.repository.PollItemRepository;
import com.marineindustryproj.service.dto.PollItemDTO;
import com.marineindustryproj.service.mapper.PollItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing PollItem.
 */
@Service
@Transactional
public class PollItemServiceImpl implements PollItemService {

    private final Logger log = LoggerFactory.getLogger(PollItemServiceImpl.class);

    private final PollItemRepository pollItemRepository;

    private final PollItemMapper pollItemMapper;

    public PollItemServiceImpl(PollItemRepository pollItemRepository, PollItemMapper pollItemMapper) {
        this.pollItemRepository = pollItemRepository;
        this.pollItemMapper = pollItemMapper;
    }

    /**
     * Save a pollItem.
     *
     * @param pollItemDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PollItemDTO save(PollItemDTO pollItemDTO) {
        log.debug("Request to save PollItem : {}", pollItemDTO);

        PollItem pollItem = pollItemMapper.toEntity(pollItemDTO);
        pollItem = pollItemRepository.save(pollItem);
        return pollItemMapper.toDto(pollItem);
    }

    /**
     * Get all the pollItems.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PollItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PollItems");
        return pollItemRepository.findAll(pageable)
            .map(pollItemMapper::toDto);
    }


    /**
     * Get one pollItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PollItemDTO> findOne(Long id) {
        log.debug("Request to get PollItem : {}", id);
        return pollItemRepository.findById(id)
            .map(pollItemMapper::toDto);
    }

    /**
     * Delete the pollItem by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PollItem : {}", id);
        pollItemRepository.deleteById(id);
    }
}
