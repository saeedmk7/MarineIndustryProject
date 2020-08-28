package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.SoldierMediaAwarenessReportService;
import com.marineindustryproj.domain.SoldierMediaAwarenessReport;
import com.marineindustryproj.repository.SoldierMediaAwarenessReportRepository;
import com.marineindustryproj.service.dto.SoldierMediaAwarenessReportDTO;
import com.marineindustryproj.service.mapper.SoldierMediaAwarenessReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SoldierMediaAwarenessReport.
 */
@Service
@Transactional
public class SoldierMediaAwarenessReportServiceImpl implements SoldierMediaAwarenessReportService {

    private final Logger log = LoggerFactory.getLogger(SoldierMediaAwarenessReportServiceImpl.class);

    private final SoldierMediaAwarenessReportRepository soldierMediaAwarenessReportRepository;

    private final SoldierMediaAwarenessReportMapper soldierMediaAwarenessReportMapper;

    public SoldierMediaAwarenessReportServiceImpl(SoldierMediaAwarenessReportRepository soldierMediaAwarenessReportRepository, SoldierMediaAwarenessReportMapper soldierMediaAwarenessReportMapper) {
        this.soldierMediaAwarenessReportRepository = soldierMediaAwarenessReportRepository;
        this.soldierMediaAwarenessReportMapper = soldierMediaAwarenessReportMapper;
    }

    /**
     * Save a soldierMediaAwarenessReport.
     *
     * @param soldierMediaAwarenessReportDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SoldierMediaAwarenessReportDTO save(SoldierMediaAwarenessReportDTO soldierMediaAwarenessReportDTO) {
        log.debug("Request to save SoldierMediaAwarenessReport : {}", soldierMediaAwarenessReportDTO);

        SoldierMediaAwarenessReport soldierMediaAwarenessReport = soldierMediaAwarenessReportMapper.toEntity(soldierMediaAwarenessReportDTO);
        soldierMediaAwarenessReport = soldierMediaAwarenessReportRepository.save(soldierMediaAwarenessReport);
        return soldierMediaAwarenessReportMapper.toDto(soldierMediaAwarenessReport);
    }

    /**
     * Get all the soldierMediaAwarenessReports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SoldierMediaAwarenessReportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SoldierMediaAwarenessReports");
        return soldierMediaAwarenessReportRepository.findAll(pageable)
            .map(soldierMediaAwarenessReportMapper::toDto);
    }

    /**
     * Get all the SoldierMediaAwarenessReport with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<SoldierMediaAwarenessReportDTO> findAllWithEagerRelationships(Pageable pageable) {
        return soldierMediaAwarenessReportRepository.findAllWithEagerRelationships(pageable).map(soldierMediaAwarenessReportMapper::toDto);
    }
    

    /**
     * Get one soldierMediaAwarenessReport by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SoldierMediaAwarenessReportDTO> findOne(Long id) {
        log.debug("Request to get SoldierMediaAwarenessReport : {}", id);
        return soldierMediaAwarenessReportRepository.findOneWithEagerRelationships(id)
            .map(soldierMediaAwarenessReportMapper::toDto);
    }

    /**
     * Delete the soldierMediaAwarenessReport by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SoldierMediaAwarenessReport : {}", id);
        soldierMediaAwarenessReportRepository.deleteById(id);
    }
}
