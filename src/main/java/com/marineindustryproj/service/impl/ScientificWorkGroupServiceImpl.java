package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.ScientificWorkGroupService;
import com.marineindustryproj.domain.ScientificWorkGroup;
import com.marineindustryproj.repository.ScientificWorkGroupRepository;
import com.marineindustryproj.service.dto.ScientificWorkGroupDTO;
import com.marineindustryproj.service.mapper.ScientificWorkGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ScientificWorkGroup.
 */
@Service
@Transactional
public class ScientificWorkGroupServiceImpl implements ScientificWorkGroupService {

    private final Logger log = LoggerFactory.getLogger(ScientificWorkGroupServiceImpl.class);

    private final ScientificWorkGroupRepository scientificWorkGroupRepository;

    private final ScientificWorkGroupMapper scientificWorkGroupMapper;

    public ScientificWorkGroupServiceImpl(ScientificWorkGroupRepository scientificWorkGroupRepository, ScientificWorkGroupMapper scientificWorkGroupMapper) {
        this.scientificWorkGroupRepository = scientificWorkGroupRepository;
        this.scientificWorkGroupMapper = scientificWorkGroupMapper;
    }

    /**
     * Save a scientificWorkGroup.
     *
     * @param scientificWorkGroupDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ScientificWorkGroupDTO save(ScientificWorkGroupDTO scientificWorkGroupDTO) {
        log.debug("Request to save ScientificWorkGroup : {}", scientificWorkGroupDTO);

        ScientificWorkGroup scientificWorkGroup = scientificWorkGroupMapper.toEntity(scientificWorkGroupDTO);
        scientificWorkGroup = scientificWorkGroupRepository.save(scientificWorkGroup);
        return scientificWorkGroupMapper.toDto(scientificWorkGroup);
    }

    /**
     * Get all the scientificWorkGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ScientificWorkGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ScientificWorkGroups");
        return scientificWorkGroupRepository.findAll(pageable)
            .map(scientificWorkGroupMapper::toDto);
    }


    /**
     * Get one scientificWorkGroup by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ScientificWorkGroupDTO> findOne(Long id) {
        log.debug("Request to get ScientificWorkGroup : {}", id);
        return scientificWorkGroupRepository.findById(id)
            .map(scientificWorkGroupMapper::toDto);
    }

    /**
     * Delete the scientificWorkGroup by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ScientificWorkGroup : {}", id);
        scientificWorkGroupRepository.deleteById(id);
    }
}
