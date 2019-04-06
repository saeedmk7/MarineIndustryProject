package com.marineindustryproj.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.marineindustryproj.domain.OrganizationChartAuthority;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.OrganizationChartAuthorityRepository;
import com.marineindustryproj.service.dto.OrganizationChartAuthorityCriteria;
import com.marineindustryproj.service.dto.OrganizationChartAuthorityDTO;
import com.marineindustryproj.service.mapper.OrganizationChartAuthorityMapper;

/**
 * Service for executing complex queries for OrganizationChartAuthority entities in the database.
 * The main input is a {@link OrganizationChartAuthorityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrganizationChartAuthorityDTO} or a {@link Page} of {@link OrganizationChartAuthorityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrganizationChartAuthorityQueryService extends QueryService<OrganizationChartAuthority> {

    private final Logger log = LoggerFactory.getLogger(OrganizationChartAuthorityQueryService.class);

    private final OrganizationChartAuthorityRepository organizationChartAuthorityRepository;

    private final OrganizationChartAuthorityMapper organizationChartAuthorityMapper;

    public OrganizationChartAuthorityQueryService(OrganizationChartAuthorityRepository organizationChartAuthorityRepository, OrganizationChartAuthorityMapper organizationChartAuthorityMapper) {
        this.organizationChartAuthorityRepository = organizationChartAuthorityRepository;
        this.organizationChartAuthorityMapper = organizationChartAuthorityMapper;
    }

    /**
     * Return a {@link List} of {@link OrganizationChartAuthorityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrganizationChartAuthorityDTO> findByCriteria(OrganizationChartAuthorityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OrganizationChartAuthority> specification = createSpecification(criteria);
        return organizationChartAuthorityMapper.toDto(organizationChartAuthorityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrganizationChartAuthorityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrganizationChartAuthorityDTO> findByCriteria(OrganizationChartAuthorityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OrganizationChartAuthority> specification = createSpecification(criteria);
        return organizationChartAuthorityRepository.findAll(specification, page)
            .map(organizationChartAuthorityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OrganizationChartAuthorityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<OrganizationChartAuthority> specification = createSpecification(criteria);
        return organizationChartAuthorityRepository.count(specification);
    }

    /**
     * Function to convert OrganizationChartAuthorityCriteria to a {@link Specification}
     */
    private Specification<OrganizationChartAuthority> createSpecification(OrganizationChartAuthorityCriteria criteria) {
        Specification<OrganizationChartAuthority> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrganizationChartAuthority_.id));
            }
            if (criteria.getAuthorityName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAuthorityName(), OrganizationChartAuthority_.authorityName));
            }
            if (criteria.getHasEditPermission() != null) {
                specification = specification.and(buildSpecification(criteria.getHasEditPermission(), OrganizationChartAuthority_.hasEditPermission));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), OrganizationChartAuthority_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), OrganizationChartAuthority_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), OrganizationChartAuthority_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), OrganizationChartAuthority_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), OrganizationChartAuthority_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), OrganizationChartAuthority_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), OrganizationChartAuthority_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), OrganizationChartAuthority_.status));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(OrganizationChartAuthority_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
        }
        return specification;
    }
}
