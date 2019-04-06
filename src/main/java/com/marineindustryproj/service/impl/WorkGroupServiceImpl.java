package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.WorkGroupService;
import com.marineindustryproj.domain.WorkGroup;
import com.marineindustryproj.repository.WorkGroupRepository;
import com.marineindustryproj.service.dto.WorkGroupDTO;
import com.marineindustryproj.service.mapper.WorkGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing WorkGroup.
 */
@Service
@Transactional
public class WorkGroupServiceImpl implements WorkGroupService {

    private final Logger log = LoggerFactory.getLogger(WorkGroupServiceImpl.class);

    private final WorkGroupRepository workGroupRepository;

    private final WorkGroupMapper workGroupMapper;

    public WorkGroupServiceImpl(WorkGroupRepository workGroupRepository, WorkGroupMapper workGroupMapper) {
        this.workGroupRepository = workGroupRepository;
        this.workGroupMapper = workGroupMapper;
    }

    /**
     * Save a workGroup.
     *
     * @param workGroupDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WorkGroupDTO save(WorkGroupDTO workGroupDTO) {
        log.debug("Request to save WorkGroup : {}", workGroupDTO);

        WorkGroup workGroup = workGroupMapper.toEntity(workGroupDTO);
        workGroup = workGroupRepository.save(workGroup);
        return workGroupMapper.toDto(workGroup);
    }

    /**
     * Get all the workGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WorkGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkGroups");
        return workGroupRepository.findAll(pageable)
            .map(workGroupMapper::toDto);
    }


    /**
     * Get one workGroup by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WorkGroupDTO> findOne(Long id) {
        log.debug("Request to get WorkGroup : {}", id);
        return workGroupRepository.findById(id)
            .map(workGroupMapper::toDto);
    }

    /**
     * Delete the workGroup by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WorkGroup : {}", id);
        workGroupRepository.deleteById(id);
    }
}
