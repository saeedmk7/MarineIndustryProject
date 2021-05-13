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

import com.marineindustryproj.domain.MatchingRunRunningStep;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.MatchingRunRunningStepRepository;
import com.marineindustryproj.service.dto.MatchingRunRunningStepCriteria;
import com.marineindustryproj.service.dto.MatchingRunRunningStepDTO;
import com.marineindustryproj.service.mapper.MatchingRunRunningStepMapper;

/**
 * Service for executing complex queries for MatchingRunRunningStep entities in the database.
 * The main input is a {@link MatchingRunRunningStepCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MatchingRunRunningStepDTO} or a {@link Page} of {@link MatchingRunRunningStepDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MatchingRunRunningStepQueryService extends QueryService<MatchingRunRunningStep> {

    private final Logger log = LoggerFactory.getLogger(MatchingRunRunningStepQueryService.class);

    private final MatchingRunRunningStepRepository matchingRunRunningStepRepository;

    private final MatchingRunRunningStepMapper matchingRunRunningStepMapper;

    public MatchingRunRunningStepQueryService(MatchingRunRunningStepRepository matchingRunRunningStepRepository, MatchingRunRunningStepMapper matchingRunRunningStepMapper) {
        this.matchingRunRunningStepRepository = matchingRunRunningStepRepository;
        this.matchingRunRunningStepMapper = matchingRunRunningStepMapper;
    }

    /**
     * Return a {@link List} of {@link MatchingRunRunningStepDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MatchingRunRunningStepDTO> findByCriteria(MatchingRunRunningStepCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MatchingRunRunningStep> specification = createSpecification(criteria);
        return matchingRunRunningStepMapper.toDto(matchingRunRunningStepRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MatchingRunRunningStepDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MatchingRunRunningStepDTO> findByCriteria(MatchingRunRunningStepCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MatchingRunRunningStep> specification = createSpecification(criteria);
        return matchingRunRunningStepRepository.findAll(specification, page)
            .map(matchingRunRunningStepMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MatchingRunRunningStepCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MatchingRunRunningStep> specification = createSpecification(criteria);
        return matchingRunRunningStepRepository.count(specification);
    }

    /**
     * Function to convert MatchingRunRunningStepCriteria to a {@link Specification}
     */
    private Specification<MatchingRunRunningStep> createSpecification(MatchingRunRunningStepCriteria criteria) {
        Specification<MatchingRunRunningStep> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MatchingRunRunningStep_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), MatchingRunRunningStep_.description));
            }
            if (criteria.getDone() != null) {
                specification = specification.and(buildSpecification(criteria.getDone(), MatchingRunRunningStep_.done));
            }
            if (criteria.getDoneUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDoneUserLogin(), MatchingRunRunningStep_.doneUserLogin));
            }
            if (criteria.getDoneDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDoneDate(), MatchingRunRunningStep_.doneDate));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), MatchingRunRunningStep_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), MatchingRunRunningStep_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), MatchingRunRunningStep_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), MatchingRunRunningStep_.modifyDate));
            }
            if (criteria.getMatchingEducationalRecordId() != null) {
                specification = specification.and(buildSpecification(criteria.getMatchingEducationalRecordId(),
                    root -> root.join(MatchingRunRunningStep_.matchingEducationalRecord, JoinType.LEFT).get(MatchingEducationalRecord_.id)));
            }
            if (criteria.getMatchingRunningStepId() != null) {
                specification = specification.and(buildSpecification(criteria.getMatchingRunningStepId(),
                    root -> root.join(MatchingRunRunningStep_.matchingRunningStep, JoinType.LEFT).get(MatchingRunningStep_.id)));
            }
        }
        return specification;
    }
}
