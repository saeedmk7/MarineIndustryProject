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

import com.marineindustryproj.domain.RunRunningStep;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.RunRunningStepRepository;
import com.marineindustryproj.service.dto.RunRunningStepCriteria;
import com.marineindustryproj.service.dto.RunRunningStepDTO;
import com.marineindustryproj.service.mapper.RunRunningStepMapper;

/**
 * Service for executing complex queries for RunRunningStep entities in the database.
 * The main input is a {@link RunRunningStepCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RunRunningStepDTO} or a {@link Page} of {@link RunRunningStepDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RunRunningStepQueryService extends QueryService<RunRunningStep> {

    private final Logger log = LoggerFactory.getLogger(RunRunningStepQueryService.class);

    private final RunRunningStepRepository runRunningStepRepository;

    private final RunRunningStepMapper runRunningStepMapper;

    public RunRunningStepQueryService(RunRunningStepRepository runRunningStepRepository, RunRunningStepMapper runRunningStepMapper) {
        this.runRunningStepRepository = runRunningStepRepository;
        this.runRunningStepMapper = runRunningStepMapper;
    }

    /**
     * Return a {@link List} of {@link RunRunningStepDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RunRunningStepDTO> findByCriteria(RunRunningStepCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RunRunningStep> specification = createSpecification(criteria);
        return runRunningStepMapper.toDto(runRunningStepRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RunRunningStepDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RunRunningStepDTO> findByCriteria(RunRunningStepCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RunRunningStep> specification = createSpecification(criteria);
        return runRunningStepRepository.findAll(specification, page)
            .map(runRunningStepMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RunRunningStepCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RunRunningStep> specification = createSpecification(criteria);
        return runRunningStepRepository.count(specification);
    }

    /**
     * Function to convert RunRunningStepCriteria to a {@link Specification}
     */
    private Specification<RunRunningStep> createSpecification(RunRunningStepCriteria criteria) {
        Specification<RunRunningStep> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RunRunningStep_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), RunRunningStep_.description));
            }
            if (criteria.getDone() != null) {
                specification = specification.and(buildSpecification(criteria.getDone(), RunRunningStep_.done));
            }
            if (criteria.getDoneUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDoneUserLogin(), RunRunningStep_.doneUserLogin));
            }
            if (criteria.getDoneDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDoneDate(), RunRunningStep_.doneDate));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), RunRunningStep_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), RunRunningStep_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), RunRunningStep_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), RunRunningStep_.modifyDate));
            }
            if (criteria.getRunPhaseId() != null) {
                specification = specification.and(buildSpecification(criteria.getRunPhaseId(),
                    root -> root.join(RunRunningStep_.runPhase, JoinType.LEFT).get(RunPhase_.id)));
            }
            if (criteria.getRunningStepId() != null) {
                specification = specification.and(buildSpecification(criteria.getRunningStepId(),
                    root -> root.join(RunRunningStep_.runningStep, JoinType.LEFT).get(RunningStep_.id)));
            }
        }
        return specification;
    }
}
