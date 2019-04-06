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

import com.marineindustryproj.domain.RunningStep;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.RunningStepRepository;
import com.marineindustryproj.service.dto.RunningStepCriteria;
import com.marineindustryproj.service.dto.RunningStepDTO;
import com.marineindustryproj.service.mapper.RunningStepMapper;

/**
 * Service for executing complex queries for RunningStep entities in the database.
 * The main input is a {@link RunningStepCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RunningStepDTO} or a {@link Page} of {@link RunningStepDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RunningStepQueryService extends QueryService<RunningStep> {

    private final Logger log = LoggerFactory.getLogger(RunningStepQueryService.class);

    private final RunningStepRepository runningStepRepository;

    private final RunningStepMapper runningStepMapper;

    public RunningStepQueryService(RunningStepRepository runningStepRepository, RunningStepMapper runningStepMapper) {
        this.runningStepRepository = runningStepRepository;
        this.runningStepMapper = runningStepMapper;
    }

    /**
     * Return a {@link List} of {@link RunningStepDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RunningStepDTO> findByCriteria(RunningStepCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RunningStep> specification = createSpecification(criteria);
        return runningStepMapper.toDto(runningStepRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RunningStepDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RunningStepDTO> findByCriteria(RunningStepCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RunningStep> specification = createSpecification(criteria);
        return runningStepRepository.findAll(specification, page)
            .map(runningStepMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RunningStepCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RunningStep> specification = createSpecification(criteria);
        return runningStepRepository.count(specification);
    }

    /**
     * Function to convert RunningStepCriteria to a {@link Specification}
     */
    private Specification<RunningStep> createSpecification(RunningStepCriteria criteria) {
        Specification<RunningStep> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RunningStep_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), RunningStep_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), RunningStep_.description));
            }
            if (criteria.getStepNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStepNumber(), RunningStep_.stepNumber));
            }
            if (criteria.getStepRequired() != null) {
                specification = specification.and(buildSpecification(criteria.getStepRequired(), RunningStep_.stepRequired));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), RunningStep_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), RunningStep_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), RunningStep_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), RunningStep_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), RunningStep_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), RunningStep_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), RunningStep_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), RunningStep_.status));
            }
            if (criteria.getRunRunningStepId() != null) {
                specification = specification.and(buildSpecification(criteria.getRunRunningStepId(),
                    root -> root.join(RunningStep_.runRunningSteps, JoinType.LEFT).get(RunRunningStep_.id)));
            }
        }
        return specification;
    }
}
