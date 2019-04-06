package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.ScoreItemService;
import com.marineindustryproj.domain.ScoreItem;
import com.marineindustryproj.repository.ScoreItemRepository;
import com.marineindustryproj.service.dto.ScoreItemDTO;
import com.marineindustryproj.service.mapper.ScoreItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ScoreItem.
 */
@Service
@Transactional
public class ScoreItemServiceImpl implements ScoreItemService {

    private final Logger log = LoggerFactory.getLogger(ScoreItemServiceImpl.class);

    private final ScoreItemRepository scoreItemRepository;

    private final ScoreItemMapper scoreItemMapper;

    public ScoreItemServiceImpl(ScoreItemRepository scoreItemRepository, ScoreItemMapper scoreItemMapper) {
        this.scoreItemRepository = scoreItemRepository;
        this.scoreItemMapper = scoreItemMapper;
    }

    /**
     * Save a scoreItem.
     *
     * @param scoreItemDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ScoreItemDTO save(ScoreItemDTO scoreItemDTO) {
        log.debug("Request to save ScoreItem : {}", scoreItemDTO);

        ScoreItem scoreItem = scoreItemMapper.toEntity(scoreItemDTO);
        scoreItem = scoreItemRepository.save(scoreItem);
        return scoreItemMapper.toDto(scoreItem);
    }

    /**
     * Get all the scoreItems.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ScoreItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ScoreItems");
        return scoreItemRepository.findAll(pageable)
            .map(scoreItemMapper::toDto);
    }


    /**
     * Get one scoreItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ScoreItemDTO> findOne(Long id) {
        log.debug("Request to get ScoreItem : {}", id);
        return scoreItemRepository.findById(id)
            .map(scoreItemMapper::toDto);
    }

    /**
     * Delete the scoreItem by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ScoreItem : {}", id);
        scoreItemRepository.deleteById(id);
    }
}
