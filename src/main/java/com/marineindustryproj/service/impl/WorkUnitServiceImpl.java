package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.WorkUnitService;
import com.marineindustryproj.domain.WorkUnit;
import com.marineindustryproj.repository.WorkUnitRepository;
import com.marineindustryproj.service.dto.WorkUnitDTO;
import com.marineindustryproj.service.mapper.WorkUnitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing WorkUnit.
 */
@Service
@Transactional
public class WorkUnitServiceImpl implements WorkUnitService {

    private final Logger log = LoggerFactory.getLogger(WorkUnitServiceImpl.class);

    private final WorkUnitRepository workUnitRepository;

    private final WorkUnitMapper workUnitMapper;

    public WorkUnitServiceImpl(WorkUnitRepository workUnitRepository, WorkUnitMapper workUnitMapper) {
        this.workUnitRepository = workUnitRepository;
        this.workUnitMapper = workUnitMapper;
    }

    /**
     * Save a workUnit.
     *
     * @param workUnitDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WorkUnitDTO save(WorkUnitDTO workUnitDTO) {
        log.debug("Request to save WorkUnit : {}", workUnitDTO);

        WorkUnit workUnit = workUnitMapper.toEntity(workUnitDTO);
        workUnit = workUnitRepository.save(workUnit);
        return workUnitMapper.toDto(workUnit);
    }

    /**
     * Get all the workUnits.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WorkUnitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkUnits");
        return workUnitRepository.findAll(pageable)
            .map(workUnitMapper::toDto);
    }


    /**
     * Get one workUnit by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WorkUnitDTO> findOne(Long id) {
        log.debug("Request to get WorkUnit : {}", id);
        return workUnitRepository.findById(id)
            .map(workUnitMapper::toDto);
    }

    /**
     * Delete the workUnit by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WorkUnit : {}", id);
        workUnitRepository.deleteById(id);
    }
}
