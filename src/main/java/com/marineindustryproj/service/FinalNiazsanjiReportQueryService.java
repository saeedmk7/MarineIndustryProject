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

import com.marineindustryproj.domain.FinalNiazsanjiReport;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.FinalNiazsanjiReportRepository;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportCriteria;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportDTO;
import com.marineindustryproj.service.mapper.FinalNiazsanjiReportMapper;

/**
 * Service for executing complex queries for FinalNiazsanjiReport entities in the database.
 * The main input is a {@link FinalNiazsanjiReportCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FinalNiazsanjiReportDTO} or a {@link Page} of {@link FinalNiazsanjiReportDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FinalNiazsanjiReportQueryService extends QueryService<FinalNiazsanjiReport> {

    private final Logger log = LoggerFactory.getLogger(FinalNiazsanjiReportQueryService.class);

    private final FinalNiazsanjiReportRepository finalNiazsanjiReportRepository;

    private final FinalNiazsanjiReportMapper finalNiazsanjiReportMapper;

    public FinalNiazsanjiReportQueryService(FinalNiazsanjiReportRepository finalNiazsanjiReportRepository, FinalNiazsanjiReportMapper finalNiazsanjiReportMapper) {
        this.finalNiazsanjiReportRepository = finalNiazsanjiReportRepository;
        this.finalNiazsanjiReportMapper = finalNiazsanjiReportMapper;
    }

    /**
     * Return a {@link List} of {@link FinalNiazsanjiReportDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FinalNiazsanjiReportDTO> findByCriteria(FinalNiazsanjiReportCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FinalNiazsanjiReport> specification = createSpecification(criteria);
        return finalNiazsanjiReportMapper.toDto(finalNiazsanjiReportRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FinalNiazsanjiReportDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FinalNiazsanjiReportDTO> findByCriteria(FinalNiazsanjiReportCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FinalNiazsanjiReport> specification = createSpecification(criteria);
        return finalNiazsanjiReportRepository.findAll(specification, page)
            .map(finalNiazsanjiReportMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FinalNiazsanjiReportCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FinalNiazsanjiReport> specification = createSpecification(criteria);
        return finalNiazsanjiReportRepository.count(specification);
    }

    /**
     * Function to convert FinalNiazsanjiReportCriteria to a {@link Specification}
     */
    private Specification<FinalNiazsanjiReport> createSpecification(FinalNiazsanjiReportCriteria criteria) {
        Specification<FinalNiazsanjiReport> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FinalNiazsanjiReport_.id));
            }
            if (criteria.getNiazsanjiYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNiazsanjiYear(), FinalNiazsanjiReport_.niazsanjiYear));
            }
            if (criteria.getNiazSanjiSource() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazSanjiSource(), FinalNiazsanjiReport_.niazSanjiSource));
            }
            if (criteria.getPriceCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPriceCost(), FinalNiazsanjiReport_.priceCost));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), FinalNiazsanjiReport_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), FinalNiazsanjiReport_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), FinalNiazsanjiReport_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), FinalNiazsanjiReport_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), FinalNiazsanjiReport_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), FinalNiazsanjiReport_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), FinalNiazsanjiReport_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), FinalNiazsanjiReport_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), FinalNiazsanjiReport_.status));
            }
            if (criteria.getRunMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRunMonth(), FinalNiazsanjiReport_.runMonth));
            }
            if (criteria.getPlanningRunMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPlanningRunMonth(), FinalNiazsanjiReport_.planningRunMonth));
            }
            if (criteria.getFinalizeCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFinalizeCost(), FinalNiazsanjiReport_.finalizeCost));
            }
            if (criteria.getFinalNiazsanjiReportPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalNiazsanjiReportPersonId(),
                    root -> root.join(FinalNiazsanjiReport_.finalNiazsanjiReportPeople, JoinType.LEFT).get(FinalNiazsanjiReportPerson_.id)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(FinalNiazsanjiReport_.finalNiazsanjiReportPeople, JoinType.LEFT).get(FinalNiazsanjiReportPerson_.person).get(Person_.id)));
            }
            if (criteria.getDesignAndPlanningId() != null) {
                specification = specification.and(buildSpecification(criteria.getDesignAndPlanningId(),
                    root -> root.join(FinalNiazsanjiReport_.designAndPlannings, JoinType.LEFT).get(DesignAndPlanning_.id)));
            }
            if (criteria.getRunPhaseId() != null) {
                specification = specification.and(buildSpecification(criteria.getRunPhaseId(),
                    root -> root.join(FinalNiazsanjiReport_.runPhases, JoinType.LEFT).get(RunPhase_.id)));
            }
            if (criteria.getPollId() != null) {
                specification = specification.and(buildSpecification(criteria.getPollId(),
                    root -> root.join(FinalNiazsanjiReport_.polls, JoinType.LEFT).get(Poll_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(FinalNiazsanjiReport_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getCourseTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCourseTypeId(),
                    root -> root.join(FinalNiazsanjiReport_.courseType, JoinType.LEFT).get(CourseType_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(FinalNiazsanjiReport_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(FinalNiazsanjiReport_.educationalModule, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getEducationalModuleTitle() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleTitle(),
                    root -> root.join(FinalNiazsanjiReport_.educationalModule, JoinType.LEFT).get(EducationalModule_.title)));
            }
        }
        return specification;
    }
}
