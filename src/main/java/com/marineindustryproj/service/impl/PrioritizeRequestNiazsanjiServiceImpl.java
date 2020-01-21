package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.NiazsanjiIntegrationService;
import com.marineindustryproj.service.PrioritizeRequestNiazsanjiService;
import com.marineindustryproj.domain.PrioritizeRequestNiazsanji;
import com.marineindustryproj.repository.PrioritizeRequestNiazsanjiRepository;
import com.marineindustryproj.service.dto.NiazsanjiIntegrationDTO;
import com.marineindustryproj.service.dto.PrioritizeRequestNiazsanjiDTO;
import com.marineindustryproj.service.mapper.PrioritizeRequestNiazsanjiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Implementation for managing PrioritizeRequestNiazsanji.
 */
@Service
@Transactional
public class PrioritizeRequestNiazsanjiServiceImpl implements PrioritizeRequestNiazsanjiService {

    private final Logger log = LoggerFactory.getLogger(PrioritizeRequestNiazsanjiServiceImpl.class);

    private final PrioritizeRequestNiazsanjiRepository prioritizeRequestNiazsanjiRepository;

    private final PrioritizeRequestNiazsanjiMapper prioritizeRequestNiazsanjiMapper;

    private final NiazsanjiIntegrationService niazsanjiIntegrationService;

    public PrioritizeRequestNiazsanjiServiceImpl(PrioritizeRequestNiazsanjiRepository prioritizeRequestNiazsanjiRepository, PrioritizeRequestNiazsanjiMapper prioritizeRequestNiazsanjiMapper, NiazsanjiIntegrationService niazsanjiIntegrationService) {
        this.prioritizeRequestNiazsanjiRepository = prioritizeRequestNiazsanjiRepository;
        this.prioritizeRequestNiazsanjiMapper = prioritizeRequestNiazsanjiMapper;
        this.niazsanjiIntegrationService = niazsanjiIntegrationService;
    }

    /**
     * Save a prioritizeRequestNiazsanji.
     *
     * @param prioritizeRequestNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PrioritizeRequestNiazsanjiDTO save(PrioritizeRequestNiazsanjiDTO prioritizeRequestNiazsanjiDTO) {
        log.debug("Request to save PrioritizeRequestNiazsanji : {}", prioritizeRequestNiazsanjiDTO);

        PrioritizeRequestNiazsanji prioritizeRequestNiazsanji = prioritizeRequestNiazsanjiMapper.toEntity(prioritizeRequestNiazsanjiDTO);
        prioritizeRequestNiazsanji = prioritizeRequestNiazsanjiRepository.save(prioritizeRequestNiazsanji);
        return prioritizeRequestNiazsanjiMapper.toDto(prioritizeRequestNiazsanji);
    }

    /**
     * Save a prioritizeRequestNiazsanji.
     *
     * @param prioritizeRequestNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PrioritizeRequestNiazsanjiDTO finalize(PrioritizeRequestNiazsanjiDTO prioritizeRequestNiazsanjiDTO) {
        log.debug("Request to save PrioritizeRequestNiazsanji : {}", prioritizeRequestNiazsanjiDTO);

        NiazsanjiIntegrationDTO niazsanjiIntegrationDTO = new NiazsanjiIntegrationDTO();
        niazsanjiIntegrationDTO.setCreateDate(ZonedDateTime.now());
        niazsanjiIntegrationDTO.setModifyDate(ZonedDateTime.now());
        niazsanjiIntegrationDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        niazsanjiIntegrationDTO.setArchived(false);
        niazsanjiIntegrationDTO.setStatus(0);
        niazsanjiIntegrationDTO.setRequestStatus(RequestStatus.READ);
        niazsanjiIntegrationDTO.setGuid(UUID.randomUUID().toString());
        niazsanjiIntegrationDTO.setHasImportantMessage(prioritizeRequestNiazsanjiDTO.isHasImportantMessage());
        niazsanjiIntegrationDTO.setRequestNiazsanjiType(prioritizeRequestNiazsanjiDTO.getRequestNiazsanjiType());
        niazsanjiIntegrationDTO.setPriority(prioritizeRequestNiazsanjiDTO.getPriority());
        niazsanjiIntegrationService.save(niazsanjiIntegrationDTO);

        prioritizeRequestNiazsanjiDTO.setRequestStatus(RequestStatus.READ);

        PrioritizeRequestNiazsanji prioritizeRequestNiazsanji = prioritizeRequestNiazsanjiMapper.toEntity(prioritizeRequestNiazsanjiDTO);
        prioritizeRequestNiazsanji = prioritizeRequestNiazsanjiRepository.save(prioritizeRequestNiazsanji);
        return prioritizeRequestNiazsanjiMapper.toDto(prioritizeRequestNiazsanji);
    }

    /**
     * Get all the prioritizeRequestNiazsanjis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PrioritizeRequestNiazsanjiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PrioritizeRequestNiazsanjis");
        return prioritizeRequestNiazsanjiRepository.findAll(pageable)
            .map(prioritizeRequestNiazsanjiMapper::toDto);
    }

    /**
     * Get all the PrioritizeRequestNiazsanji with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<PrioritizeRequestNiazsanjiDTO> findAllWithEagerRelationships(Pageable pageable) {
        return prioritizeRequestNiazsanjiRepository.findAllWithEagerRelationships(pageable).map(prioritizeRequestNiazsanjiMapper::toDto);
    }
    

    /**
     * Get one prioritizeRequestNiazsanji by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PrioritizeRequestNiazsanjiDTO> findOne(Long id) {
        log.debug("Request to get PrioritizeRequestNiazsanji : {}", id);
        return prioritizeRequestNiazsanjiRepository.findOneWithEagerRelationships(id)
            .map(prioritizeRequestNiazsanjiMapper::toDto);
    }

    /**
     * Delete the prioritizeRequestNiazsanji by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PrioritizeRequestNiazsanji : {}", id);
        prioritizeRequestNiazsanjiRepository.deleteById(id);
    }
}
