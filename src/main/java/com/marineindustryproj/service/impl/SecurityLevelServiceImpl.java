package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.SecurityLevelService;
import com.marineindustryproj.domain.SecurityLevel;
import com.marineindustryproj.repository.SecurityLevelRepository;
import com.marineindustryproj.service.dto.SecurityLevelDTO;
import com.marineindustryproj.service.mapper.SecurityLevelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SecurityLevel.
 */
@Service
@Transactional
public class SecurityLevelServiceImpl implements SecurityLevelService {

    private final Logger log = LoggerFactory.getLogger(SecurityLevelServiceImpl.class);

    private final SecurityLevelRepository securityLevelRepository;

    private final SecurityLevelMapper securityLevelMapper;

    public SecurityLevelServiceImpl(SecurityLevelRepository securityLevelRepository, SecurityLevelMapper securityLevelMapper) {
        this.securityLevelRepository = securityLevelRepository;
        this.securityLevelMapper = securityLevelMapper;
    }

    /**
     * Save a securityLevel.
     *
     * @param securityLevelDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SecurityLevelDTO save(SecurityLevelDTO securityLevelDTO) {
        log.debug("Request to save SecurityLevel : {}", securityLevelDTO);

        SecurityLevel securityLevel = securityLevelMapper.toEntity(securityLevelDTO);
        securityLevel = securityLevelRepository.save(securityLevel);
        return securityLevelMapper.toDto(securityLevel);
    }

    /**
     * Get all the securityLevels.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SecurityLevelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SecurityLevels");
        return securityLevelRepository.findAll(pageable)
            .map(securityLevelMapper::toDto);
    }


    /**
     * Get one securityLevel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SecurityLevelDTO> findOne(Long id) {
        log.debug("Request to get SecurityLevel : {}", id);
        return securityLevelRepository.findById(id)
            .map(securityLevelMapper::toDto);
    }

    /**
     * Delete the securityLevel by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SecurityLevel : {}", id);
        securityLevelRepository.deleteById(id);
    }
}
