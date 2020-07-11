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

import com.marineindustryproj.domain.ReportGenerator;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.ReportGeneratorRepository;
import com.marineindustryproj.service.dto.ReportGeneratorCriteria;
import com.marineindustryproj.service.dto.ReportGeneratorDTO;
import com.marineindustryproj.service.mapper.ReportGeneratorMapper;

/**
 * Service for executing complex queries for ReportGenerator entities in the database.
 * The main input is a {@link ReportGeneratorCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ReportGeneratorDTO} or a {@link Page} of {@link ReportGeneratorDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ReportGeneratorQueryService extends QueryService<ReportGenerator> {

    private final Logger log = LoggerFactory.getLogger(ReportGeneratorQueryService.class);

    private final ReportGeneratorRepository reportGeneratorRepository;

    private final ReportGeneratorMapper reportGeneratorMapper;

    public ReportGeneratorQueryService(ReportGeneratorRepository reportGeneratorRepository, ReportGeneratorMapper reportGeneratorMapper) {
        this.reportGeneratorRepository = reportGeneratorRepository;
        this.reportGeneratorMapper = reportGeneratorMapper;
    }

    /**
     * Return a {@link List} of {@link ReportGeneratorDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ReportGeneratorDTO> findByCriteria(ReportGeneratorCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ReportGenerator> specification = createSpecification(criteria);
        return reportGeneratorMapper.toDto(reportGeneratorRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ReportGeneratorDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ReportGeneratorDTO> findByCriteria(ReportGeneratorCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ReportGenerator> specification = createSpecification(criteria);
        return reportGeneratorRepository.findAll(specification, page)
            .map(reportGeneratorMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ReportGeneratorCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ReportGenerator> specification = createSpecification(criteria);
        return reportGeneratorRepository.count(specification);
    }

    /**
     * Function to convert ReportGeneratorCriteria to a {@link Specification}
     */
    private Specification<ReportGenerator> createSpecification(ReportGeneratorCriteria criteria) {
        Specification<ReportGenerator> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ReportGenerator_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), ReportGenerator_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ReportGenerator_.description));
            }
            if (criteria.getSearchParams() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSearchParams(), ReportGenerator_.searchParams));
            }
            if (criteria.getColumnNames() != null) {
                specification = specification.and(buildStringSpecification(criteria.getColumnNames(), ReportGenerator_.columnNames));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), ReportGenerator_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), ReportGenerator_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), ReportGenerator_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), ReportGenerator_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), ReportGenerator_.guid));
            }
            if (criteria.getReportGeneratorAuthorityId() != null) {
                specification = specification.and(buildSpecification(criteria.getReportGeneratorAuthorityId(),
                    root -> root.join(ReportGenerator_.reportGeneratorAuthorities, JoinType.LEFT).get(ReportGeneratorAuthority_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(ReportGenerator_.organizationCharts, JoinType.LEFT).get(OrganizationChart_.id)));
            }
        }
        return specification;
    }
}
