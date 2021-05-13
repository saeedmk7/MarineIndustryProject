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

import com.marineindustryproj.domain.MatchingRunningStep;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.MatchingRunningStepRepository;
import com.marineindustryproj.service.dto.MatchingRunningStepCriteria;
import com.marineindustryproj.service.dto.MatchingRunningStepDTO;
import com.marineindustryproj.service.mapper.MatchingRunningStepMapper;

/**
 * Service for executing complex queries for MatchingRunningStep entities in the database.
 * The main input is a {@link MatchingRunningStepCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MatchingRunningStepDTO} or a {@link Page} of {@link MatchingRunningStepDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MatchingRunningStepQueryService extends QueryService<MatchingRunningStep> {

    private final Logger log = LoggerFactory.getLogger(MatchingRunningStepQueryService.class);

    private final MatchingRunningStepRepository matchingRunningStepRepository;

    private final MatchingRunningStepMapper matchingRunningStepMapper;

    public MatchingRunningStepQueryService(MatchingRunningStepRepository matchingRunningStepRepository, MatchingRunningStepMapper matchingRunningStepMapper) {
        this.matchingRunningStepRepository = matchingRunningStepRepository;
        this.matchingRunningStepMapper = matchingRunningStepMapper;
    }

    /**
     * Return a {@link List} of {@link MatchingRunningStepDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MatchingRunningStepDTO> findByCriteria(MatchingRunningStepCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MatchingRunningStep> specification = createSpecification(criteria);
        return matchingRunningStepMapper.toDto(matchingRunningStepRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MatchingRunningStepDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MatchingRunningStepDTO> findByCriteria(MatchingRunningStepCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MatchingRunningStep> specification = createSpecification(criteria);
        return matchingRunningStepRepository.findAll(specification, page)
            .map(matchingRunningStepMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MatchingRunningStepCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MatchingRunningStep> specification = createSpecification(criteria);
        return matchingRunningStepRepository.count(specification);
    }

    /**
     * Function to convert MatchingRunningStepCriteria to a {@link Specification}
     */
    private Specification<MatchingRunningStep> createSpecification(MatchingRunningStepCriteria criteria) {
        Specification<MatchingRunningStep> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MatchingRunningStep_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), MatchingRunningStep_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), MatchingRunningStep_.description));
            }
            if (criteria.getStepNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStepNumber(), MatchingRunningStep_.stepNumber));
            }
            if (criteria.getStepRequired() != null) {
                specification = specification.and(buildSpecification(criteria.getStepRequired(), MatchingRunningStep_.stepRequired));
            }
            if (criteria.getFileDocRequired() != null) {
                specification = specification.and(buildSpecification(criteria.getFileDocRequired(), MatchingRunningStep_.fileDocRequired));
            }
            if (criteria.getColorText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getColorText(), MatchingRunningStep_.colorText));
            }
            if (criteria.getIsHeader() != null) {
                specification = specification.and(buildSpecification(criteria.getIsHeader(), MatchingRunningStep_.isHeader));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), MatchingRunningStep_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), MatchingRunningStep_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), MatchingRunningStep_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), MatchingRunningStep_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), MatchingRunningStep_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), MatchingRunningStep_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), MatchingRunningStep_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), MatchingRunningStep_.status));
            }
            if (criteria.getMatchingRunRunningStepId() != null) {
                specification = specification.and(buildSpecification(criteria.getMatchingRunRunningStepId(),
                    root -> root.join(MatchingRunningStep_.matchingRunRunningSteps, JoinType.LEFT).get(MatchingRunRunningStep_.id)));
            }
        }
        return specification;
    }
}
