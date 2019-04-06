package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.ActivityAreaService;
import com.marineindustryproj.domain.ActivityArea;
import com.marineindustryproj.repository.ActivityAreaRepository;
import com.marineindustryproj.service.dto.ActivityAreaDTO;
import com.marineindustryproj.service.mapper.ActivityAreaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ActivityArea.
 */
@Service
@Transactional
public class ActivityAreaServiceImpl implements ActivityAreaService {

    private final Logger log = LoggerFactory.getLogger(ActivityAreaServiceImpl.class);

    private final ActivityAreaRepository activityAreaRepository;

    private final ActivityAreaMapper activityAreaMapper;

    public ActivityAreaServiceImpl(ActivityAreaRepository activityAreaRepository, ActivityAreaMapper activityAreaMapper) {
        this.activityAreaRepository = activityAreaRepository;
        this.activityAreaMapper = activityAreaMapper;
    }

    /**
     * Save a activityArea.
     *
     * @param activityAreaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ActivityAreaDTO save(ActivityAreaDTO activityAreaDTO) {
        log.debug("Request to save ActivityArea : {}", activityAreaDTO);

        ActivityArea activityArea = activityAreaMapper.toEntity(activityAreaDTO);
        activityArea = activityAreaRepository.save(activityArea);
        return activityAreaMapper.toDto(activityArea);
    }

    /**
     * Get all the activityAreas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ActivityAreaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ActivityAreas");
        return activityAreaRepository.findAll(pageable)
            .map(activityAreaMapper::toDto);
    }


    /**
     * Get one activityArea by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ActivityAreaDTO> findOne(Long id) {
        log.debug("Request to get ActivityArea : {}", id);
        return activityAreaRepository.findById(id)
            .map(activityAreaMapper::toDto);
    }

    /**
     * Delete the activityArea by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ActivityArea : {}", id);
        activityAreaRepository.deleteById(id);
    }
}
