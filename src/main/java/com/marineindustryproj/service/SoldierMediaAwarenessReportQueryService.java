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

import com.marineindustryproj.domain.SoldierMediaAwarenessReport;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.SoldierMediaAwarenessReportRepository;
import com.marineindustryproj.service.dto.SoldierMediaAwarenessReportCriteria;
import com.marineindustryproj.service.dto.SoldierMediaAwarenessReportDTO;
import com.marineindustryproj.service.mapper.SoldierMediaAwarenessReportMapper;

/**
 * Service for executing complex queries for SoldierMediaAwarenessReport entities in the database.
 * The main input is a {@link SoldierMediaAwarenessReportCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SoldierMediaAwarenessReportDTO} or a {@link Page} of {@link SoldierMediaAwarenessReportDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SoldierMediaAwarenessReportQueryService extends QueryService<SoldierMediaAwarenessReport> {

    private final Logger log = LoggerFactory.getLogger(SoldierMediaAwarenessReportQueryService.class);

    private final SoldierMediaAwarenessReportRepository soldierMediaAwarenessReportRepository;

    private final SoldierMediaAwarenessReportMapper soldierMediaAwarenessReportMapper;

    public SoldierMediaAwarenessReportQueryService(SoldierMediaAwarenessReportRepository soldierMediaAwarenessReportRepository, SoldierMediaAwarenessReportMapper soldierMediaAwarenessReportMapper) {
        this.soldierMediaAwarenessReportRepository = soldierMediaAwarenessReportRepository;
        this.soldierMediaAwarenessReportMapper = soldierMediaAwarenessReportMapper;
    }

    /**
     * Return a {@link List} of {@link SoldierMediaAwarenessReportDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SoldierMediaAwarenessReportDTO> findByCriteria(SoldierMediaAwarenessReportCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SoldierMediaAwarenessReport> specification = createSpecification(criteria);
        return soldierMediaAwarenessReportMapper.toDto(soldierMediaAwarenessReportRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SoldierMediaAwarenessReportDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SoldierMediaAwarenessReportDTO> findByCriteria(SoldierMediaAwarenessReportCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SoldierMediaAwarenessReport> specification = createSpecification(criteria);
        return soldierMediaAwarenessReportRepository.findAll(specification, page)
            .map(soldierMediaAwarenessReportMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SoldierMediaAwarenessReportCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SoldierMediaAwarenessReport> specification = createSpecification(criteria);
        return soldierMediaAwarenessReportRepository.count(specification);
    }

    /**
     * Function to convert SoldierMediaAwarenessReportCriteria to a {@link Specification}
     */
    private Specification<SoldierMediaAwarenessReport> createSpecification(SoldierMediaAwarenessReportCriteria criteria) {
        Specification<SoldierMediaAwarenessReport> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SoldierMediaAwarenessReport_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), SoldierMediaAwarenessReport_.title));
            }
            if (criteria.getPersonHour() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPersonHour(), SoldierMediaAwarenessReport_.personHour));
            }
            if (criteria.getExecutiveTrainingCompany() != null) {
                specification = specification.and(buildStringSpecification(criteria.getExecutiveTrainingCompany(), SoldierMediaAwarenessReport_.executiveTrainingCompany));
            }
            if (criteria.getCertificateStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCertificateStatus(), SoldierMediaAwarenessReport_.certificateStatus));
            }
            if (criteria.getCertificateNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCertificateNumber(), SoldierMediaAwarenessReport_.certificateNumber));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), SoldierMediaAwarenessReport_.year));
            }
            if (criteria.getMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMonth(), SoldierMediaAwarenessReport_.month));
            }
            if (criteria.getReportTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReportTime(), SoldierMediaAwarenessReport_.reportTime));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), SoldierMediaAwarenessReport_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), SoldierMediaAwarenessReport_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), SoldierMediaAwarenessReport_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), SoldierMediaAwarenessReport_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), SoldierMediaAwarenessReport_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), SoldierMediaAwarenessReport_.guid));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(SoldierMediaAwarenessReport_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getSoldierId() != null) {
                specification = specification.and(buildSpecification(criteria.getSoldierId(),
                    root -> root.join(SoldierMediaAwarenessReport_.soldier, JoinType.LEFT).get(Soldier_.id)));
            }
            if (criteria.getSoldierOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getSoldierOrganizationChartId(),
                    root -> root.join(SoldierMediaAwarenessReport_.soldier, JoinType.LEFT)
                        .join(Soldier_.organizationChart, JoinType.LEFT)
                        .get(OrganizationChart_.id)));
            }
        }
        return specification;
    }
}
