package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.TeachingRecordService;
import com.marineindustryproj.domain.TeachingRecord;
import com.marineindustryproj.repository.TeachingRecordRepository;
import com.marineindustryproj.service.dto.TeachingRecordDTO;
import com.marineindustryproj.service.mapper.TeachingRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TeachingRecord.
 */
@Service
@Transactional
public class TeachingRecordServiceImpl implements TeachingRecordService {

    private final Logger log = LoggerFactory.getLogger(TeachingRecordServiceImpl.class);

    private final TeachingRecordRepository teachingRecordRepository;

    private final TeachingRecordMapper teachingRecordMapper;

    public TeachingRecordServiceImpl(TeachingRecordRepository teachingRecordRepository, TeachingRecordMapper teachingRecordMapper) {
        this.teachingRecordRepository = teachingRecordRepository;
        this.teachingRecordMapper = teachingRecordMapper;
    }

    /**
     * Save a teachingRecord.
     *
     * @param teachingRecordDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TeachingRecordDTO save(TeachingRecordDTO teachingRecordDTO) {
        log.debug("Request to save TeachingRecord : {}", teachingRecordDTO);

        TeachingRecord teachingRecord = teachingRecordMapper.toEntity(teachingRecordDTO);
        teachingRecord = teachingRecordRepository.save(teachingRecord);
        return teachingRecordMapper.toDto(teachingRecord);
    }

    /**
     * Get all the teachingRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TeachingRecordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TeachingRecords");
        return teachingRecordRepository.findAll(pageable)
            .map(teachingRecordMapper::toDto);
    }


    /**
     * Get one teachingRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TeachingRecordDTO> findOne(Long id) {
        log.debug("Request to get TeachingRecord : {}", id);
        return teachingRecordRepository.findById(id)
            .map(teachingRecordMapper::toDto);
    }

    /**
     * Delete the teachingRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TeachingRecord : {}", id);
        teachingRecordRepository.deleteById(id);
    }
}
