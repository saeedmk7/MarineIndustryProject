package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.SoldierTrainingReportService;
import com.marineindustryproj.domain.SoldierTrainingReport;
import com.marineindustryproj.repository.SoldierTrainingReportRepository;
import com.marineindustryproj.service.dto.SoldierTrainingReportDTO;
import com.marineindustryproj.service.mapper.SoldierTrainingReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SoldierTrainingReport.
 */
@Service
@Transactional
public class SoldierTrainingReportServiceImpl implements SoldierTrainingReportService {

    private final Logger log = LoggerFactory.getLogger(SoldierTrainingReportServiceImpl.class);

    private final SoldierTrainingReportRepository soldierTrainingReportRepository;

    private final SoldierTrainingReportMapper soldierTrainingReportMapper;

    public SoldierTrainingReportServiceImpl(SoldierTrainingReportRepository soldierTrainingReportRepository, SoldierTrainingReportMapper soldierTrainingReportMapper) {
        this.soldierTrainingReportRepository = soldierTrainingReportRepository;
        this.soldierTrainingReportMapper = soldierTrainingReportMapper;
    }

    /**
     * Save a soldierTrainingReport.
     *
     * @param soldierTrainingReportDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SoldierTrainingReportDTO save(SoldierTrainingReportDTO soldierTrainingReportDTO) {
        log.debug("Request to save SoldierTrainingReport : {}", soldierTrainingReportDTO);

        SoldierTrainingReport soldierTrainingReport = soldierTrainingReportMapper.toEntity(soldierTrainingReportDTO);
        soldierTrainingReport = soldierTrainingReportRepository.save(soldierTrainingReport);
        return soldierTrainingReportMapper.toDto(soldierTrainingReport);
    }

    /**
     * Get all the soldierTrainingReports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SoldierTrainingReportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SoldierTrainingReports");
        return soldierTrainingReportRepository.findAll(pageable)
            .map(soldierTrainingReportMapper::toDto);
    }

    /**
     * Get all the SoldierTrainingReport with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<SoldierTrainingReportDTO> findAllWithEagerRelationships(Pageable pageable) {
        return soldierTrainingReportRepository.findAllWithEagerRelationships(pageable).map(soldierTrainingReportMapper::toDto);
    }
    

    /**
     * Get one soldierTrainingReport by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SoldierTrainingReportDTO> findOne(Long id) {
        log.debug("Request to get SoldierTrainingReport : {}", id);
        return soldierTrainingReportRepository.findOneWithEagerRelationships(id)
            .map(soldierTrainingReportMapper::toDto);
    }

    /**
     * Delete the soldierTrainingReport by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SoldierTrainingReport : {}", id);
        soldierTrainingReportRepository.deleteById(id);
    }
}
