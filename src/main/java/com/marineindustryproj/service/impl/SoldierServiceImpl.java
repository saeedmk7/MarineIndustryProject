package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.SoldierService;
import com.marineindustryproj.domain.Soldier;
import com.marineindustryproj.repository.SoldierRepository;
import com.marineindustryproj.service.dto.SoldierDTO;
import com.marineindustryproj.service.mapper.SoldierMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Soldier.
 */
@Service
@Transactional
public class SoldierServiceImpl implements SoldierService {

    private final Logger log = LoggerFactory.getLogger(SoldierServiceImpl.class);

    private final SoldierRepository soldierRepository;

    private final SoldierMapper soldierMapper;

    public SoldierServiceImpl(SoldierRepository soldierRepository, SoldierMapper soldierMapper) {
        this.soldierRepository = soldierRepository;
        this.soldierMapper = soldierMapper;
    }

    /**
     * Save a soldier.
     *
     * @param soldierDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SoldierDTO save(SoldierDTO soldierDTO) {
        log.debug("Request to save Soldier : {}", soldierDTO);

        Soldier soldier = soldierMapper.toEntity(soldierDTO);
        soldier = soldierRepository.save(soldier);
        return soldierMapper.toDto(soldier);
    }

    /**
     * Get all the soldiers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SoldierDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Soldiers");
        return soldierRepository.findAll(pageable)
            .map(soldierMapper::toDto);
    }

    /**
     * Get all the Soldier with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<SoldierDTO> findAllWithEagerRelationships(Pageable pageable) {
        return soldierRepository.findAllWithEagerRelationships(pageable).map(soldierMapper::toDto);
    }
    

    /**
     * Get one soldier by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SoldierDTO> findOne(Long id) {
        log.debug("Request to get Soldier : {}", id);
        return soldierRepository.findOneWithEagerRelationships(id)
            .map(soldierMapper::toDto);
    }

    /**
     * Delete the soldier by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Soldier : {}", id);
        soldierRepository.deleteById(id);
    }
}
