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

import com.marineindustryproj.domain.ReportGeneratorAuthority;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.ReportGeneratorAuthorityRepository;
import com.marineindustryproj.service.dto.ReportGeneratorAuthorityCriteria;
import com.marineindustryproj.service.dto.ReportGeneratorAuthorityDTO;
import com.marineindustryproj.service.mapper.ReportGeneratorAuthorityMapper;

/**
 * Service for executing complex queries for ReportGeneratorAuthority entities in the database.
 * The main input is a {@link ReportGeneratorAuthorityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ReportGeneratorAuthorityDTO} or a {@link Page} of {@link ReportGeneratorAuthorityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ReportGeneratorAuthorityQueryService extends QueryService<ReportGeneratorAuthority> {

    private final Logger log = LoggerFactory.getLogger(ReportGeneratorAuthorityQueryService.class);

    private final ReportGeneratorAuthorityRepository reportGeneratorAuthorityRepository;

    private final ReportGeneratorAuthorityMapper reportGeneratorAuthorityMapper;

    public ReportGeneratorAuthorityQueryService(ReportGeneratorAuthorityRepository reportGeneratorAuthorityRepository, ReportGeneratorAuthorityMapper reportGeneratorAuthorityMapper) {
        this.reportGeneratorAuthorityRepository = reportGeneratorAuthorityRepository;
        this.reportGeneratorAuthorityMapper = reportGeneratorAuthorityMapper;
    }

    /**
     * Return a {@link List} of {@link ReportGeneratorAuthorityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ReportGeneratorAuthorityDTO> findByCriteria(ReportGeneratorAuthorityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ReportGeneratorAuthority> specification = createSpecification(criteria);
        return reportGeneratorAuthorityMapper.toDto(reportGeneratorAuthorityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ReportGeneratorAuthorityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ReportGeneratorAuthorityDTO> findByCriteria(ReportGeneratorAuthorityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ReportGeneratorAuthority> specification = createSpecification(criteria);
        return reportGeneratorAuthorityRepository.findAll(specification, page)
            .map(reportGeneratorAuthorityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ReportGeneratorAuthorityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ReportGeneratorAuthority> specification = createSpecification(criteria);
        return reportGeneratorAuthorityRepository.count(specification);
    }

    /**
     * Function to convert ReportGeneratorAuthorityCriteria to a {@link Specification}
     */
    private Specification<ReportGeneratorAuthority> createSpecification(ReportGeneratorAuthorityCriteria criteria) {
        Specification<ReportGeneratorAuthority> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ReportGeneratorAuthority_.id));
            }
            if (criteria.getAuthorityName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAuthorityName(), ReportGeneratorAuthority_.authorityName));
            }
            if (criteria.getHasEditPermission() != null) {
                specification = specification.and(buildSpecification(criteria.getHasEditPermission(), ReportGeneratorAuthority_.hasEditPermission));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), ReportGeneratorAuthority_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), ReportGeneratorAuthority_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), ReportGeneratorAuthority_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), ReportGeneratorAuthority_.modifyDate));
            }
            if (criteria.getReportGeneratorId() != null) {
                specification = specification.and(buildSpecification(criteria.getReportGeneratorId(),
                    root -> root.join(ReportGeneratorAuthority_.reportGenerator, JoinType.LEFT).get(ReportGenerator_.id)));
            }
        }
        return specification;
    }
}
