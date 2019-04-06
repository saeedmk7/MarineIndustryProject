package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.MainTaskService;
import com.marineindustryproj.domain.MainTask;
import com.marineindustryproj.repository.MainTaskRepository;
import com.marineindustryproj.service.dto.MainTaskDTO;
import com.marineindustryproj.service.mapper.MainTaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MainTask.
 */
@Service
@Transactional
public class MainTaskServiceImpl implements MainTaskService {

    private final Logger log = LoggerFactory.getLogger(MainTaskServiceImpl.class);

    private final MainTaskRepository mainTaskRepository;

    private final MainTaskMapper mainTaskMapper;

    public MainTaskServiceImpl(MainTaskRepository mainTaskRepository, MainTaskMapper mainTaskMapper) {
        this.mainTaskRepository = mainTaskRepository;
        this.mainTaskMapper = mainTaskMapper;
    }

    /**
     * Save a mainTask.
     *
     * @param mainTaskDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MainTaskDTO save(MainTaskDTO mainTaskDTO) {
        log.debug("Request to save MainTask : {}", mainTaskDTO);

        MainTask mainTask = mainTaskMapper.toEntity(mainTaskDTO);
        mainTask = mainTaskRepository.save(mainTask);
        return mainTaskMapper.toDto(mainTask);
    }

    /**
     * Get all the mainTasks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MainTaskDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MainTasks");
        return mainTaskRepository.findAll(pageable)
            .map(mainTaskMapper::toDto);
    }

    /**
     * Get all the MainTask with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<MainTaskDTO> findAllWithEagerRelationships(Pageable pageable) {
        return mainTaskRepository.findAllWithEagerRelationships(pageable).map(mainTaskMapper::toDto);
    }
    

    /**
     * Get one mainTask by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MainTaskDTO> findOne(Long id) {
        log.debug("Request to get MainTask : {}", id);
        return mainTaskRepository.findOneWithEagerRelationships(id)
            .map(mainTaskMapper::toDto);
    }

    /**
     * Delete the mainTask by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MainTask : {}", id);
        mainTaskRepository.deleteById(id);
    }
}
