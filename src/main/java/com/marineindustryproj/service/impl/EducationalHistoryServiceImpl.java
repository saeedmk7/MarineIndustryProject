package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EducationalHistoryService;
import com.marineindustryproj.domain.EducationalHistory;
import com.marineindustryproj.repository.EducationalHistoryRepository;
import com.marineindustryproj.service.dto.EducationalHistoryDTO;
import com.marineindustryproj.service.mapper.EducationalHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EducationalHistory.
 */
@Service
@Transactional
public class EducationalHistoryServiceImpl implements EducationalHistoryService {

    private final Logger log = LoggerFactory.getLogger(EducationalHistoryServiceImpl.class);

    private final EducationalHistoryRepository educationalHistoryRepository;

    private final EducationalHistoryMapper educationalHistoryMapper;

    public EducationalHistoryServiceImpl(EducationalHistoryRepository educationalHistoryRepository, EducationalHistoryMapper educationalHistoryMapper) {
        this.educationalHistoryRepository = educationalHistoryRepository;
        this.educationalHistoryMapper = educationalHistoryMapper;
    }

    /**
     * Save a educationalHistory.
     *
     * @param educationalHistoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EducationalHistoryDTO save(EducationalHistoryDTO educationalHistoryDTO) {
        log.debug("Request to save EducationalHistory : {}", educationalHistoryDTO);

        EducationalHistory educationalHistory = educationalHistoryMapper.toEntity(educationalHistoryDTO);
        educationalHistory = educationalHistoryRepository.save(educationalHistory);
        return educationalHistoryMapper.toDto(educationalHistory);
    }

    /**
     * Get all the educationalHistories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EducationalHistoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EducationalHistories");
        return educationalHistoryRepository.findAll(pageable)
            .map(educationalHistoryMapper::toDto);
    }


    /**
     * Get one educationalHistory by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EducationalHistoryDTO> findOne(Long id) {
        log.debug("Request to get EducationalHistory : {}", id);
        return educationalHistoryRepository.findById(id)
            .map(educationalHistoryMapper::toDto);
    }

    /**
     * Delete the educationalHistory by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EducationalHistory : {}", id);
        educationalHistoryRepository.deleteById(id);
    }
}
