package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.HeadlineService;
import com.marineindustryproj.domain.Headline;
import com.marineindustryproj.repository.HeadlineRepository;
import com.marineindustryproj.service.dto.HeadlineDTO;
import com.marineindustryproj.service.mapper.HeadlineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Headline.
 */
@Service
@Transactional
public class HeadlineServiceImpl implements HeadlineService {

    private final Logger log = LoggerFactory.getLogger(HeadlineServiceImpl.class);

    private final HeadlineRepository headlineRepository;

    private final HeadlineMapper headlineMapper;

    public HeadlineServiceImpl(HeadlineRepository headlineRepository, HeadlineMapper headlineMapper) {
        this.headlineRepository = headlineRepository;
        this.headlineMapper = headlineMapper;
    }

    /**
     * Save a headline.
     *
     * @param headlineDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HeadlineDTO save(HeadlineDTO headlineDTO) {
        log.debug("Request to save Headline : {}", headlineDTO);

        Headline headline = headlineMapper.toEntity(headlineDTO);
        headline = headlineRepository.save(headline);
        return headlineMapper.toDto(headline);
    }

    /**
     * Get all the headlines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HeadlineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Headlines");
        return headlineRepository.findAll(pageable)
            .map(headlineMapper::toDto);
    }


    /**
     * Get one headline by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HeadlineDTO> findOne(Long id) {
        log.debug("Request to get Headline : {}", id);
        return headlineRepository.findById(id)
            .map(headlineMapper::toDto);
    }

    /**
     * Delete the headline by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Headline : {}", id);
        headlineRepository.deleteById(id);
    }
}
