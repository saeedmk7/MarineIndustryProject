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

import com.marineindustryproj.domain.OrganizationChart;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.OrganizationChartRepository;
import com.marineindustryproj.service.dto.OrganizationChartCriteria;
import com.marineindustryproj.service.dto.OrganizationChartDTO;
import com.marineindustryproj.service.mapper.OrganizationChartMapper;

/**
 * Service for executing complex queries for OrganizationChart entities in the database.
 * The main input is a {@link OrganizationChartCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrganizationChartDTO} or a {@link Page} of {@link OrganizationChartDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrganizationChartQueryService extends QueryService<OrganizationChart> {

    private final Logger log = LoggerFactory.getLogger(OrganizationChartQueryService.class);

    private final OrganizationChartRepository organizationChartRepository;

    private final OrganizationChartMapper organizationChartMapper;

    public OrganizationChartQueryService(OrganizationChartRepository organizationChartRepository, OrganizationChartMapper organizationChartMapper) {
        this.organizationChartRepository = organizationChartRepository;
        this.organizationChartMapper = organizationChartMapper;
    }

    /**
     * Return a {@link List} of {@link OrganizationChartDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrganizationChartDTO> findByCriteria(OrganizationChartCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OrganizationChart> specification = createSpecification(criteria);
        return organizationChartMapper.toDto(organizationChartRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrganizationChartDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrganizationChartDTO> findByCriteria(OrganizationChartCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OrganizationChart> specification = createSpecification(criteria);
        return organizationChartRepository.findAll(specification, page)
            .map(organizationChartMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OrganizationChartCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<OrganizationChart> specification = createSpecification(criteria);
        return organizationChartRepository.count(specification);
    }

    /**
     * Function to convert OrganizationChartCriteria to a {@link Specification}
     */
    private Specification<OrganizationChart> createSpecification(OrganizationChartCriteria criteria) {
        Specification<OrganizationChart> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OrganizationChart_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), OrganizationChart_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), OrganizationChart_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), OrganizationChart_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), OrganizationChart_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), OrganizationChart_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), OrganizationChart_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), OrganizationChart_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), OrganizationChart_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), OrganizationChart_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), OrganizationChart_.status));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(OrganizationChart_.people, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(OrganizationChart_.organizationCharts, JoinType.LEFT).get(OrganizationChart_.id)));
            }
            if (criteria.getRequestOrganizationNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestOrganizationNiazsanjiId(),
                    root -> root.join(OrganizationChart_.requestOrganizationNiazsanjis, JoinType.LEFT).get(RequestOrganizationNiazsanji_.id)));
            }
            if (criteria.getFinalOrganizationNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalOrganizationNiazsanjiId(),
                    root -> root.join(OrganizationChart_.finalOrganizationNiazsanjis, JoinType.LEFT).get(FinalOrganizationNiazsanji_.id)));
            }
            if (criteria.getFinalNiazsanjiReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalNiazsanjiReportId(),
                    root -> root.join(OrganizationChart_.finalNiazsanjiReports, JoinType.LEFT).get(FinalNiazsanjiReport_.id)));
            }
            if (criteria.getDesignAndPlanningId() != null) {
                specification = specification.and(buildSpecification(criteria.getDesignAndPlanningId(),
                    root -> root.join(OrganizationChart_.designAndPlannings, JoinType.LEFT).get(DesignAndPlanning_.id)));
            }
            if (criteria.getRunPhaseId() != null) {
                specification = specification.and(buildSpecification(criteria.getRunPhaseId(),
                    root -> root.join(OrganizationChart_.runPhases, JoinType.LEFT).get(RunPhase_.id)));
            }
            if (criteria.getOrganizationChartAuthorityId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartAuthorityId(),
                    root -> root.join(OrganizationChart_.organizationChartAuthorities, JoinType.LEFT).get(OrganizationChartAuthority_.id)));
            }
            if (criteria.getNiazsanjiFardiId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiFardiId(),
                    root -> root.join(OrganizationChart_.niazsanjiFardis, JoinType.LEFT).get(NiazsanjiFardi_.id)));
            }
            if (criteria.getRequestNiazsanjiFardiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestNiazsanjiFardiId(),
                    root -> root.join(OrganizationChart_.requestNiazsanjiFardis, JoinType.LEFT).get(RequestNiazsanjiFardi_.id)));
            }
            if (criteria.getEducationalHistoryId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalHistoryId(),
                    root -> root.join(OrganizationChart_.educationalHistories, JoinType.LEFT).get(EducationalHistory_.id)));
            }
            if (criteria.getInvestToGroupTransactionId() != null) {
                specification = specification.and(buildSpecification(criteria.getInvestToGroupTransactionId(),
                    root -> root.join(OrganizationChart_.investToGroupTransactions, JoinType.LEFT).get(InvestToGroupTransaction_.id)));
            }
            if (criteria.getMediaAwarenessReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getMediaAwarenessReportId(),
                    root -> root.join(OrganizationChart_.mediaAwarenessReports, JoinType.LEFT).get(MediaAwarenessReport_.id)));
            }
            if (criteria.getPreJobNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getPreJobNiazsanjiId(),
                    root -> root.join(OrganizationChart_.preJobNiazsanjis, JoinType.LEFT).get(PreJobNiazsanji_.id)));
            }
            if (criteria.getJobNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getJobNiazsanjiId(),
                    root -> root.join(OrganizationChart_.jobNiazsanjis, JoinType.LEFT).get(JobNiazsanji_.id)));
            }
            if (criteria.getNiazsanjiOtherId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiOtherId(),
                    root -> root.join(OrganizationChart_.niazsanjiOthers, JoinType.LEFT).get(NiazsanjiOther_.id)));
            }
            if (criteria.getRequestOtherNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestOtherNiazsanjiId(),
                    root -> root.join(OrganizationChart_.requestOtherNiazsanjis, JoinType.LEFT).get(RequestOtherNiazsanji_.id)));
            }
            if (criteria.getPrioritizeRequestNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getPrioritizeRequestNiazsanjiId(),
                    root -> root.join(OrganizationChart_.prioritizeRequestNiazsanjis, JoinType.LEFT).get(PrioritizeRequestNiazsanji_.id)));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(OrganizationChart_.parent, JoinType.LEFT).get(OrganizationChart_.id)));
            }
            if (criteria.getForceRunningPercentId() != null) {
                specification = specification.and(buildSpecification(criteria.getForceRunningPercentId(),
                    root -> root.join(OrganizationChart_.forceRunningPercents, JoinType.LEFT).get(ForceRunningPercent_.id)));
            }
        }
        return specification;
    }
}
