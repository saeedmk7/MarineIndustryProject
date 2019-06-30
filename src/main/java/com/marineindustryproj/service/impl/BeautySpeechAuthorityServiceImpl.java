package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.BeautySpeechAuthorityQueryService;
import com.marineindustryproj.service.BeautySpeechAuthorityService;
import com.marineindustryproj.domain.BeautySpeechAuthority;
import com.marineindustryproj.repository.BeautySpeechAuthorityRepository;
import com.marineindustryproj.service.dto.BeautySpeechAuthorityCriteria;
import com.marineindustryproj.service.dto.BeautySpeechAuthorityDTO;
import com.marineindustryproj.service.mapper.BeautySpeechAuthorityMapper;
import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing BeautySpeechAuthority.
 */
@Service
@Transactional
public class BeautySpeechAuthorityServiceImpl implements BeautySpeechAuthorityService {

    private final Logger log = LoggerFactory.getLogger(BeautySpeechAuthorityServiceImpl.class);

    private final BeautySpeechAuthorityRepository beautySpeechAuthorityRepository;

    private final BeautySpeechAuthorityMapper beautySpeechAuthorityMapper;

    private final BeautySpeechAuthorityQueryService beautySpeechAuthorityQueryService;

    public BeautySpeechAuthorityServiceImpl(BeautySpeechAuthorityRepository beautySpeechAuthorityRepository, BeautySpeechAuthorityMapper beautySpeechAuthorityMapper, BeautySpeechAuthorityQueryService beautySpeechAuthorityQueryService) {
        this.beautySpeechAuthorityRepository = beautySpeechAuthorityRepository;
        this.beautySpeechAuthorityMapper = beautySpeechAuthorityMapper;
        this.beautySpeechAuthorityQueryService = beautySpeechAuthorityQueryService;
    }

    /**
     * Save a beautySpeechAuthority.
     *
     * @param beautySpeechAuthorityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BeautySpeechAuthorityDTO save(BeautySpeechAuthorityDTO beautySpeechAuthorityDTO) {
        log.debug("Request to save BeautySpeechAuthority : {}", beautySpeechAuthorityDTO);

        BeautySpeechAuthority beautySpeechAuthority = beautySpeechAuthorityMapper.toEntity(beautySpeechAuthorityDTO);
        beautySpeechAuthority = beautySpeechAuthorityRepository.save(beautySpeechAuthority);
        return beautySpeechAuthorityMapper.toDto(beautySpeechAuthority);
    }

    /**
     * Get all the beautySpeechAuthorities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BeautySpeechAuthorityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BeautySpeechAuthorities");
        return beautySpeechAuthorityRepository.findAll(pageable)
            .map(beautySpeechAuthorityMapper::toDto);
    }


    /**
     * Get one beautySpeechAuthority by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BeautySpeechAuthorityDTO> findOne(Long id) {
        log.debug("Request to get BeautySpeechAuthority : {}", id);
        return beautySpeechAuthorityRepository.findById(id)
            .map(beautySpeechAuthorityMapper::toDto);
    }

    /**
     * Delete the beautySpeechAuthority by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BeautySpeechAuthority : {}", id);
        beautySpeechAuthorityRepository.deleteById(id);
    }
    /**
     * Delete the beautySpeechAuthority by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void deleteByBeautySpeechId(Long id) {
        log.debug("Request to delete BeautySpeechAuthority : {}", id);

        BeautySpeechAuthorityCriteria beautySpeechAuthorityCriteria = new BeautySpeechAuthorityCriteria();
        LongFilter longFilter = new LongFilter();
        longFilter.setEquals(id);
        beautySpeechAuthorityCriteria.setBeautySpeechId(longFilter);
        List<BeautySpeechAuthorityDTO> beautySpeechAuthorityList = beautySpeechAuthorityQueryService.findByCriteria(beautySpeechAuthorityCriteria);
        if(!beautySpeechAuthorityList.isEmpty())
        {
            for (BeautySpeechAuthorityDTO beautySpeechAuthority: beautySpeechAuthorityList) {
                beautySpeechAuthorityRepository.deleteById(beautySpeechAuthority.getId());
            }
        }
    }
}
