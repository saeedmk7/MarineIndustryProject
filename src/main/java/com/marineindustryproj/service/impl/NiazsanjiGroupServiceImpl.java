package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.NiazsanjiGroupService;
import com.marineindustryproj.domain.NiazsanjiGroup;
import com.marineindustryproj.repository.NiazsanjiGroupRepository;
import com.marineindustryproj.service.dto.NiazsanjiGroupDTO;
import com.marineindustryproj.service.mapper.NiazsanjiGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing NiazsanjiGroup.
 */
@Service
@Transactional
public class NiazsanjiGroupServiceImpl implements NiazsanjiGroupService {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiGroupServiceImpl.class);

    private final NiazsanjiGroupRepository niazsanjiGroupRepository;

    private final NiazsanjiGroupMapper niazsanjiGroupMapper;

    public NiazsanjiGroupServiceImpl(NiazsanjiGroupRepository niazsanjiGroupRepository, NiazsanjiGroupMapper niazsanjiGroupMapper) {
        this.niazsanjiGroupRepository = niazsanjiGroupRepository;
        this.niazsanjiGroupMapper = niazsanjiGroupMapper;
    }

    /**
     * Save a niazsanjiGroup.
     *
     * @param niazsanjiGroupDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NiazsanjiGroupDTO save(NiazsanjiGroupDTO niazsanjiGroupDTO) {
        log.debug("Request to save NiazsanjiGroup : {}", niazsanjiGroupDTO);

        NiazsanjiGroup niazsanjiGroup = niazsanjiGroupMapper.toEntity(niazsanjiGroupDTO);
        niazsanjiGroup = niazsanjiGroupRepository.save(niazsanjiGroup);
        return niazsanjiGroupMapper.toDto(niazsanjiGroup);
    }

    /**
     * Get all the niazsanjiGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NiazsanjiGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NiazsanjiGroups");
        return niazsanjiGroupRepository.findAll(pageable)
            .map(niazsanjiGroupMapper::toDto);
    }

    /**
     * Get all the NiazsanjiGroup with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<NiazsanjiGroupDTO> findAllWithEagerRelationships(Pageable pageable) {
        return niazsanjiGroupRepository.findAllWithEagerRelationships(pageable).map(niazsanjiGroupMapper::toDto);
    }
    

    /**
     * Get one niazsanjiGroup by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NiazsanjiGroupDTO> findOne(Long id) {
        log.debug("Request to get NiazsanjiGroup : {}", id);
        return niazsanjiGroupRepository.findOneWithEagerRelationships(id)
            .map(niazsanjiGroupMapper::toDto);
    }

    /**
     * Delete the niazsanjiGroup by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NiazsanjiGroup : {}", id);
        niazsanjiGroupRepository.deleteById(id);
    }
}
