package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.ResearchRecordService;
import com.marineindustryproj.domain.ResearchRecord;
import com.marineindustryproj.repository.ResearchRecordRepository;
import com.marineindustryproj.service.dto.ResearchRecordDTO;
import com.marineindustryproj.service.mapper.ResearchRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ResearchRecord.
 */
@Service
@Transactional
public class ResearchRecordServiceImpl implements ResearchRecordService {

    private final Logger log = LoggerFactory.getLogger(ResearchRecordServiceImpl.class);

    private final ResearchRecordRepository researchRecordRepository;

    private final ResearchRecordMapper researchRecordMapper;

    public ResearchRecordServiceImpl(ResearchRecordRepository researchRecordRepository, ResearchRecordMapper researchRecordMapper) {
        this.researchRecordRepository = researchRecordRepository;
        this.researchRecordMapper = researchRecordMapper;
    }

    /**
     * Save a researchRecord.
     *
     * @param researchRecordDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ResearchRecordDTO save(ResearchRecordDTO researchRecordDTO) {
        log.debug("Request to save ResearchRecord : {}", researchRecordDTO);

        ResearchRecord researchRecord = researchRecordMapper.toEntity(researchRecordDTO);
        researchRecord = researchRecordRepository.save(researchRecord);
        return researchRecordMapper.toDto(researchRecord);
    }

    /**
     * Get all the researchRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ResearchRecordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ResearchRecords");
        return researchRecordRepository.findAll(pageable)
            .map(researchRecordMapper::toDto);
    }


    /**
     * Get one researchRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ResearchRecordDTO> findOne(Long id) {
        log.debug("Request to get ResearchRecord : {}", id);
        return researchRecordRepository.findById(id)
            .map(researchRecordMapper::toDto);
    }

    /**
     * Delete the researchRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ResearchRecord : {}", id);
        researchRecordRepository.deleteById(id);
    }
}
