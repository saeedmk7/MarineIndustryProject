package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.SubTaskService;
import com.marineindustryproj.domain.SubTask;
import com.marineindustryproj.repository.SubTaskRepository;
import com.marineindustryproj.service.dto.SubTaskDTO;
import com.marineindustryproj.service.mapper.SubTaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SubTask.
 */
@Service
@Transactional
public class SubTaskServiceImpl implements SubTaskService {

    private final Logger log = LoggerFactory.getLogger(SubTaskServiceImpl.class);

    private final SubTaskRepository subTaskRepository;

    private final SubTaskMapper subTaskMapper;

    public SubTaskServiceImpl(SubTaskRepository subTaskRepository, SubTaskMapper subTaskMapper) {
        this.subTaskRepository = subTaskRepository;
        this.subTaskMapper = subTaskMapper;
    }

    /**
     * Save a subTask.
     *
     * @param subTaskDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SubTaskDTO save(SubTaskDTO subTaskDTO) {
        log.debug("Request to save SubTask : {}", subTaskDTO);

        SubTask subTask = subTaskMapper.toEntity(subTaskDTO);
        subTask = subTaskRepository.save(subTask);
        return subTaskMapper.toDto(subTask);
    }

    /**
     * Get all the subTasks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SubTaskDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SubTasks");
        return subTaskRepository.findAll(pageable)
            .map(subTaskMapper::toDto);
    }


    /**
     * Get one subTask by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SubTaskDTO> findOne(Long id) {
        log.debug("Request to get SubTask : {}", id);
        return subTaskRepository.findById(id)
            .map(subTaskMapper::toDto);
    }

    /**
     * Delete the subTask by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SubTask : {}", id);
        subTaskRepository.deleteById(id);
    }
}
