package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.DesignAndPlanningService;
import com.marineindustryproj.domain.DesignAndPlanning;
import com.marineindustryproj.repository.DesignAndPlanningRepository;
import com.marineindustryproj.service.dto.DesignAndPlanningDTO;
import com.marineindustryproj.service.mapper.DesignAndPlanningMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing DesignAndPlanning.
 */
@Service
@Transactional
public class DesignAndPlanningServiceImpl implements DesignAndPlanningService {

    private final Logger log = LoggerFactory.getLogger(DesignAndPlanningServiceImpl.class);

    private final DesignAndPlanningRepository designAndPlanningRepository;

    private final DesignAndPlanningMapper designAndPlanningMapper;

    public DesignAndPlanningServiceImpl(DesignAndPlanningRepository designAndPlanningRepository, DesignAndPlanningMapper designAndPlanningMapper) {
        this.designAndPlanningRepository = designAndPlanningRepository;
        this.designAndPlanningMapper = designAndPlanningMapper;
    }

    /**
     * Save a designAndPlanning.
     *
     * @param designAndPlanningDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DesignAndPlanningDTO save(DesignAndPlanningDTO designAndPlanningDTO) {
        log.debug("Request to save DesignAndPlanning : {}", designAndPlanningDTO);

        DesignAndPlanning designAndPlanning = designAndPlanningMapper.toEntity(designAndPlanningDTO);
        designAndPlanning = designAndPlanningRepository.save(designAndPlanning);
        return designAndPlanningMapper.toDto(designAndPlanning);
    }

    /**
     * Get all the designAndPlannings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DesignAndPlanningDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DesignAndPlannings");
        return designAndPlanningRepository.findAll(pageable)
            .map(designAndPlanningMapper::toDto);
    }

    /**
     * Get all the DesignAndPlanning with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<DesignAndPlanningDTO> findAllWithEagerRelationships(Pageable pageable) {
        return designAndPlanningRepository.findAllWithEagerRelationships(pageable).map(designAndPlanningMapper::toDto);
    }
    

    /**
     * Get one designAndPlanning by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DesignAndPlanningDTO> findOne(Long id) {
        log.debug("Request to get DesignAndPlanning : {}", id);
        return designAndPlanningRepository.findOneWithEagerRelationships(id)
            .map(designAndPlanningMapper::toDto);
    }

    /**
     * Delete the designAndPlanning by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DesignAndPlanning : {}", id);
        designAndPlanningRepository.deleteById(id);
    }
}
