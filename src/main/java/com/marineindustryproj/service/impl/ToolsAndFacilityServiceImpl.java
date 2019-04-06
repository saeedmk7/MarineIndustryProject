package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.ToolsAndFacilityService;
import com.marineindustryproj.domain.ToolsAndFacility;
import com.marineindustryproj.repository.ToolsAndFacilityRepository;
import com.marineindustryproj.service.dto.ToolsAndFacilityDTO;
import com.marineindustryproj.service.mapper.ToolsAndFacilityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ToolsAndFacility.
 */
@Service
@Transactional
public class ToolsAndFacilityServiceImpl implements ToolsAndFacilityService {

    private final Logger log = LoggerFactory.getLogger(ToolsAndFacilityServiceImpl.class);

    private final ToolsAndFacilityRepository toolsAndFacilityRepository;

    private final ToolsAndFacilityMapper toolsAndFacilityMapper;

    public ToolsAndFacilityServiceImpl(ToolsAndFacilityRepository toolsAndFacilityRepository, ToolsAndFacilityMapper toolsAndFacilityMapper) {
        this.toolsAndFacilityRepository = toolsAndFacilityRepository;
        this.toolsAndFacilityMapper = toolsAndFacilityMapper;
    }

    /**
     * Save a toolsAndFacility.
     *
     * @param toolsAndFacilityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ToolsAndFacilityDTO save(ToolsAndFacilityDTO toolsAndFacilityDTO) {
        log.debug("Request to save ToolsAndFacility : {}", toolsAndFacilityDTO);

        ToolsAndFacility toolsAndFacility = toolsAndFacilityMapper.toEntity(toolsAndFacilityDTO);
        toolsAndFacility = toolsAndFacilityRepository.save(toolsAndFacility);
        return toolsAndFacilityMapper.toDto(toolsAndFacility);
    }

    /**
     * Get all the toolsAndFacilities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ToolsAndFacilityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ToolsAndFacilities");
        return toolsAndFacilityRepository.findAll(pageable)
            .map(toolsAndFacilityMapper::toDto);
    }


    /**
     * Get one toolsAndFacility by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ToolsAndFacilityDTO> findOne(Long id) {
        log.debug("Request to get ToolsAndFacility : {}", id);
        return toolsAndFacilityRepository.findById(id)
            .map(toolsAndFacilityMapper::toDto);
    }

    /**
     * Delete the toolsAndFacility by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ToolsAndFacility : {}", id);
        toolsAndFacilityRepository.deleteById(id);
    }
}
