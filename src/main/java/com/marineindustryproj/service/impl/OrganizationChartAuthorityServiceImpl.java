package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.OrganizationChartAuthorityService;
import com.marineindustryproj.domain.OrganizationChartAuthority;
import com.marineindustryproj.repository.OrganizationChartAuthorityRepository;
import com.marineindustryproj.service.dto.OrganizationChartAuthorityDTO;
import com.marineindustryproj.service.mapper.OrganizationChartAuthorityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing OrganizationChartAuthority.
 */
@Service
@Transactional
public class OrganizationChartAuthorityServiceImpl implements OrganizationChartAuthorityService {

    private final Logger log = LoggerFactory.getLogger(OrganizationChartAuthorityServiceImpl.class);

    private final OrganizationChartAuthorityRepository organizationChartAuthorityRepository;

    private final OrganizationChartAuthorityMapper organizationChartAuthorityMapper;

    public OrganizationChartAuthorityServiceImpl(OrganizationChartAuthorityRepository organizationChartAuthorityRepository, OrganizationChartAuthorityMapper organizationChartAuthorityMapper) {
        this.organizationChartAuthorityRepository = organizationChartAuthorityRepository;
        this.organizationChartAuthorityMapper = organizationChartAuthorityMapper;
    }

    /**
     * Save a organizationChartAuthority.
     *
     * @param organizationChartAuthorityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrganizationChartAuthorityDTO save(OrganizationChartAuthorityDTO organizationChartAuthorityDTO) {
        log.debug("Request to save OrganizationChartAuthority : {}", organizationChartAuthorityDTO);

        OrganizationChartAuthority organizationChartAuthority = organizationChartAuthorityMapper.toEntity(organizationChartAuthorityDTO);
        organizationChartAuthority = organizationChartAuthorityRepository.save(organizationChartAuthority);
        return organizationChartAuthorityMapper.toDto(organizationChartAuthority);
    }

    /**
     * Get all the organizationChartAuthorities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrganizationChartAuthorityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrganizationChartAuthorities");
        return organizationChartAuthorityRepository.findAll(pageable)
            .map(organizationChartAuthorityMapper::toDto);
    }


    /**
     * Get one organizationChartAuthority by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrganizationChartAuthorityDTO> findOne(Long id) {
        log.debug("Request to get OrganizationChartAuthority : {}", id);
        return organizationChartAuthorityRepository.findById(id)
            .map(organizationChartAuthorityMapper::toDto);
    }

    /**
     * Delete the organizationChartAuthority by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrganizationChartAuthority : {}", id);
        organizationChartAuthorityRepository.deleteById(id);
    }
}
