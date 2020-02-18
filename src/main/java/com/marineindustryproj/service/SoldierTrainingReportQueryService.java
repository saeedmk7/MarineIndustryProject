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

import com.marineindustryproj.domain.SoldierTrainingReport;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.SoldierTrainingReportRepository;
import com.marineindustryproj.service.dto.SoldierTrainingReportCriteria;
import com.marineindustryproj.service.dto.SoldierTrainingReportDTO;
import com.marineindustryproj.service.mapper.SoldierTrainingReportMapper;

/**
 * Service for executing complex queries for SoldierTrainingReport entities in the database.
 * The main input is a {@link SoldierTrainingReportCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SoldierTrainingReportDTO} or a {@link Page} of {@link SoldierTrainingReportDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SoldierTrainingReportQueryService extends QueryService<SoldierTrainingReport> {

    private final Logger log = LoggerFactory.getLogger(SoldierTrainingReportQueryService.class);

    private final SoldierTrainingReportRepository soldierTrainingReportRepository;

    private final SoldierTrainingReportMapper soldierTrainingReportMapper;

    public SoldierTrainingReportQueryService(SoldierTrainingReportRepository soldierTrainingReportRepository, SoldierTrainingReportMapper soldierTrainingReportMapper) {
        this.soldierTrainingReportRepository = soldierTrainingReportRepository;
        this.soldierTrainingReportMapper = soldierTrainingReportMapper;
    }

    /**
     * Return a {@link List} of {@link SoldierTrainingReportDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SoldierTrainingReportDTO> findByCriteria(SoldierTrainingReportCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SoldierTrainingReport> specification = createSpecification(criteria);
        return soldierTrainingReportMapper.toDto(soldierTrainingReportRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SoldierTrainingReportDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SoldierTrainingReportDTO> findByCriteria(SoldierTrainingReportCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SoldierTrainingReport> specification = createSpecification(criteria);
        return soldierTrainingReportRepository.findAll(specification, page)
            .map(soldierTrainingReportMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SoldierTrainingReportCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SoldierTrainingReport> specification = createSpecification(criteria);
        return soldierTrainingReportRepository.count(specification);
    }

    /**
     * Function to convert SoldierTrainingReportCriteria to a {@link Specification}
     */
    private Specification<SoldierTrainingReport> createSpecification(SoldierTrainingReportCriteria criteria) {
        Specification<SoldierTrainingReport> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SoldierTrainingReport_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), SoldierTrainingReport_.title));
            }
            if (criteria.getPersonHour() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPersonHour(), SoldierTrainingReport_.personHour));
            }
            if (criteria.getExecutiveTrainingCompany() != null) {
                specification = specification.and(buildStringSpecification(criteria.getExecutiveTrainingCompany(), SoldierTrainingReport_.executiveTrainingCompany));
            }
            if (criteria.getCertificateStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCertificateStatus(), SoldierTrainingReport_.certificateStatus));
            }
            if (criteria.getCertificateNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCertificateNumber(), SoldierTrainingReport_.certificateNumber));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), SoldierTrainingReport_.year));
            }
            if (criteria.getMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMonth(), SoldierTrainingReport_.month));
            }
            if (criteria.getReportTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReportTime(), SoldierTrainingReport_.reportTime));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), SoldierTrainingReport_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), SoldierTrainingReport_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), SoldierTrainingReport_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), SoldierTrainingReport_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), SoldierTrainingReport_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), SoldierTrainingReport_.guid));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(SoldierTrainingReport_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getSoldierId() != null) {
                specification = specification.and(buildSpecification(criteria.getSoldierId(),
                    root -> root.join(SoldierTrainingReport_.soldier, JoinType.LEFT).get(Soldier_.id)));
            }
            if (criteria.getSoldierOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getSoldierOrganizationChartId(),
                    root -> root.join(SoldierTrainingReport_.soldier, JoinType.LEFT)
                        .join(Soldier_.organizationChart, JoinType.LEFT)
                        .get(OrganizationChart_.id)));
            }
        }
        return specification;
    }
}
