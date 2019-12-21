package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.RestrictionService;
import com.marineindustryproj.domain.Restriction;
import com.marineindustryproj.repository.RestrictionRepository;
import com.marineindustryproj.service.dto.RestrictionDTO;
import com.marineindustryproj.service.mapper.RestrictionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Restriction.
 */
@Service
@Transactional
public class RestrictionServiceImpl implements RestrictionService {

    private final Logger log = LoggerFactory.getLogger(RestrictionServiceImpl.class);

    private final RestrictionRepository restrictionRepository;

    private final RestrictionMapper restrictionMapper;

    public RestrictionServiceImpl(RestrictionRepository restrictionRepository, RestrictionMapper restrictionMapper) {
        this.restrictionRepository = restrictionRepository;
        this.restrictionMapper = restrictionMapper;
    }

    /**
     * Save a restriction.
     *
     * @param restrictionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RestrictionDTO save(RestrictionDTO restrictionDTO) {
        log.debug("Request to save Restriction : {}", restrictionDTO);

        Restriction restriction = restrictionMapper.toEntity(restrictionDTO);
        restriction = restrictionRepository.save(restriction);
        return restrictionMapper.toDto(restriction);
    }

    /**
     * Get all the restrictions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RestrictionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Restrictions");
        return restrictionRepository.findAll(pageable)
            .map(restrictionMapper::toDto);
    }

    /**
     * Get one restriction by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RestrictionDTO> findOne(Long id) {
        log.debug("Request to get Restriction : {}", id);
        return restrictionRepository.findById(id)
            .map(restrictionMapper::toDto);
    }

    /**
     * Delete the restriction by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Restriction : {}", id);
        restrictionRepository.deleteById(id);
    }
}
