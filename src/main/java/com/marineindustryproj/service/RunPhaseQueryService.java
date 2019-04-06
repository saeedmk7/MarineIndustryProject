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

import com.marineindustryproj.domain.RunPhase;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.RunPhaseRepository;
import com.marineindustryproj.service.dto.RunPhaseCriteria;
import com.marineindustryproj.service.dto.RunPhaseDTO;
import com.marineindustryproj.service.mapper.RunPhaseMapper;

/**
 * Service for executing complex queries for RunPhase entities in the database.
 * The main input is a {@link RunPhaseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RunPhaseDTO} or a {@link Page} of {@link RunPhaseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RunPhaseQueryService extends QueryService<RunPhase> {

    private final Logger log = LoggerFactory.getLogger(RunPhaseQueryService.class);

    private final RunPhaseRepository runPhaseRepository;

    private final RunPhaseMapper runPhaseMapper;

    public RunPhaseQueryService(RunPhaseRepository runPhaseRepository, RunPhaseMapper runPhaseMapper) {
        this.runPhaseRepository = runPhaseRepository;
        this.runPhaseMapper = runPhaseMapper;
    }

    /**
     * Return a {@link List} of {@link RunPhaseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RunPhaseDTO> findByCriteria(RunPhaseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RunPhase> specification = createSpecification(criteria);
        return runPhaseMapper.toDto(runPhaseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RunPhaseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RunPhaseDTO> findByCriteria(RunPhaseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RunPhase> specification = createSpecification(criteria);
        return runPhaseRepository.findAll(specification, page)
            .map(runPhaseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RunPhaseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RunPhase> specification = createSpecification(criteria);
        return runPhaseRepository.count(specification);
    }

    /**
     * Function to convert RunPhaseCriteria to a {@link Specification}
     */
    private Specification<RunPhase> createSpecification(RunPhaseCriteria criteria) {
        Specification<RunPhase> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RunPhase_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), RunPhase_.description));
            }
            if (criteria.getFinalizeCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFinalizeCost(), RunPhase_.finalizeCost));
            }
            if (criteria.getStepNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStepNumber(), RunPhase_.stepNumber));
            }
            if (criteria.getDone() != null) {
                specification = specification.and(buildSpecification(criteria.getDone(), RunPhase_.done));
            }
            if (criteria.getDoneUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDoneUserLogin(), RunPhase_.doneUserLogin));
            }
            if (criteria.getDoneDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDoneDate(), RunPhase_.doneDate));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), RunPhase_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), RunPhase_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), RunPhase_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), RunPhase_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), RunPhase_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), RunPhase_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), RunPhase_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), RunPhase_.status));
            }
            if (criteria.getRunRunningStepId() != null) {
                specification = specification.and(buildSpecification(criteria.getRunRunningStepId(),
                    root -> root.join(RunPhase_.runRunningSteps, JoinType.LEFT).get(RunRunningStep_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(RunPhase_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(RunPhase_.people, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getFinalNiazsanjiReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalNiazsanjiReportId(),
                    root -> root.join(RunPhase_.finalNiazsanjiReport, JoinType.LEFT).get(FinalNiazsanjiReport_.id)));
            }
        }
        return specification;
    }
}
