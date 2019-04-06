package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.WorkIndustryService;
import com.marineindustryproj.domain.WorkIndustry;
import com.marineindustryproj.repository.WorkIndustryRepository;
import com.marineindustryproj.service.dto.WorkIndustryDTO;
import com.marineindustryproj.service.mapper.WorkIndustryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing WorkIndustry.
 */
@Service
@Transactional
public class WorkIndustryServiceImpl implements WorkIndustryService {

    private final Logger log = LoggerFactory.getLogger(WorkIndustryServiceImpl.class);

    private final WorkIndustryRepository workIndustryRepository;

    private final WorkIndustryMapper workIndustryMapper;

    public WorkIndustryServiceImpl(WorkIndustryRepository workIndustryRepository, WorkIndustryMapper workIndustryMapper) {
        this.workIndustryRepository = workIndustryRepository;
        this.workIndustryMapper = workIndustryMapper;
    }

    /**
     * Save a workIndustry.
     *
     * @param workIndustryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WorkIndustryDTO save(WorkIndustryDTO workIndustryDTO) {
        log.debug("Request to save WorkIndustry : {}", workIndustryDTO);

        WorkIndustry workIndustry = workIndustryMapper.toEntity(workIndustryDTO);
        workIndustry = workIndustryRepository.save(workIndustry);
        return workIndustryMapper.toDto(workIndustry);
    }

    /**
     * Get all the workIndustries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WorkIndustryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkIndustries");
        return workIndustryRepository.findAll(pageable)
            .map(workIndustryMapper::toDto);
    }


    /**
     * Get one workIndustry by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WorkIndustryDTO> findOne(Long id) {
        log.debug("Request to get WorkIndustry : {}", id);
        return workIndustryRepository.findById(id)
            .map(workIndustryMapper::toDto);
    }

    /**
     * Delete the workIndustry by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WorkIndustry : {}", id);
        workIndustryRepository.deleteById(id);
    }
}
