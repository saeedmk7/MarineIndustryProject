package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EducationalRecordService;
import com.marineindustryproj.domain.EducationalRecord;
import com.marineindustryproj.repository.EducationalRecordRepository;
import com.marineindustryproj.service.dto.EducationalRecordDTO;
import com.marineindustryproj.service.mapper.EducationalRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EducationalRecord.
 */
@Service
@Transactional
public class EducationalRecordServiceImpl implements EducationalRecordService {

    private final Logger log = LoggerFactory.getLogger(EducationalRecordServiceImpl.class);

    private final EducationalRecordRepository educationalRecordRepository;

    private final EducationalRecordMapper educationalRecordMapper;

    public EducationalRecordServiceImpl(EducationalRecordRepository educationalRecordRepository, EducationalRecordMapper educationalRecordMapper) {
        this.educationalRecordRepository = educationalRecordRepository;
        this.educationalRecordMapper = educationalRecordMapper;
    }

    /**
     * Save a educationalRecord.
     *
     * @param educationalRecordDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EducationalRecordDTO save(EducationalRecordDTO educationalRecordDTO) {
        log.debug("Request to save EducationalRecord : {}", educationalRecordDTO);

        EducationalRecord educationalRecord = educationalRecordMapper.toEntity(educationalRecordDTO);
        educationalRecord = educationalRecordRepository.save(educationalRecord);
        return educationalRecordMapper.toDto(educationalRecord);
    }

    /**
     * Get all the educationalRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EducationalRecordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EducationalRecords");
        return educationalRecordRepository.findAll(pageable)
            .map(educationalRecordMapper::toDto);
    }


    /**
     * Get one educationalRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EducationalRecordDTO> findOne(Long id) {
        log.debug("Request to get EducationalRecord : {}", id);
        return educationalRecordRepository.findById(id)
            .map(educationalRecordMapper::toDto);
    }

    /**
     * Delete the educationalRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EducationalRecord : {}", id);
        educationalRecordRepository.deleteById(id);
    }
}
