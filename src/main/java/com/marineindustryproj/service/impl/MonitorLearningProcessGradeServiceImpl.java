package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.MonitorLearningProcessGradeService;
import com.marineindustryproj.domain.MonitorLearningProcessGrade;
import com.marineindustryproj.repository.MonitorLearningProcessGradeRepository;
import com.marineindustryproj.service.dto.MonitorLearningProcessGradeDTO;
import com.marineindustryproj.service.mapper.MonitorLearningProcessGradeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MonitorLearningProcessGrade.
 */
@Service
@Transactional
public class MonitorLearningProcessGradeServiceImpl implements MonitorLearningProcessGradeService {

    private final Logger log = LoggerFactory.getLogger(MonitorLearningProcessGradeServiceImpl.class);

    private final MonitorLearningProcessGradeRepository monitorLearningProcessGradeRepository;

    private final MonitorLearningProcessGradeMapper monitorLearningProcessGradeMapper;

    public MonitorLearningProcessGradeServiceImpl(MonitorLearningProcessGradeRepository monitorLearningProcessGradeRepository, MonitorLearningProcessGradeMapper monitorLearningProcessGradeMapper) {
        this.monitorLearningProcessGradeRepository = monitorLearningProcessGradeRepository;
        this.monitorLearningProcessGradeMapper = monitorLearningProcessGradeMapper;
    }

    /**
     * Save a monitorLearningProcessGrade.
     *
     * @param monitorLearningProcessGradeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MonitorLearningProcessGradeDTO save(MonitorLearningProcessGradeDTO monitorLearningProcessGradeDTO) {
        log.debug("Request to save MonitorLearningProcessGrade : {}", monitorLearningProcessGradeDTO);

        MonitorLearningProcessGrade monitorLearningProcessGrade = monitorLearningProcessGradeMapper.toEntity(monitorLearningProcessGradeDTO);
        monitorLearningProcessGrade = monitorLearningProcessGradeRepository.save(monitorLearningProcessGrade);
        return monitorLearningProcessGradeMapper.toDto(monitorLearningProcessGrade);
    }

    /**
     * Get all the monitorLearningProcessGrades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MonitorLearningProcessGradeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MonitorLearningProcessGrades");
        return monitorLearningProcessGradeRepository.findAll(pageable)
            .map(monitorLearningProcessGradeMapper::toDto);
    }


    /**
     * Get one monitorLearningProcessGrade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MonitorLearningProcessGradeDTO> findOne(Long id) {
        log.debug("Request to get MonitorLearningProcessGrade : {}", id);
        return monitorLearningProcessGradeRepository.findById(id)
            .map(monitorLearningProcessGradeMapper::toDto);
    }

    /**
     * Delete the monitorLearningProcessGrade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MonitorLearningProcessGrade : {}", id);
        monitorLearningProcessGradeRepository.deleteById(id);
    }
}
