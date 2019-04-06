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

import com.marineindustryproj.domain.Goal;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.GoalRepository;
import com.marineindustryproj.service.dto.GoalCriteria;
import com.marineindustryproj.service.dto.GoalDTO;
import com.marineindustryproj.service.mapper.GoalMapper;

/**
 * Service for executing complex queries for Goal entities in the database.
 * The main input is a {@link GoalCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link GoalDTO} or a {@link Page} of {@link GoalDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class GoalQueryService extends QueryService<Goal> {

    private final Logger log = LoggerFactory.getLogger(GoalQueryService.class);

    private final GoalRepository goalRepository;

    private final GoalMapper goalMapper;

    public GoalQueryService(GoalRepository goalRepository, GoalMapper goalMapper) {
        this.goalRepository = goalRepository;
        this.goalMapper = goalMapper;
    }

    /**
     * Return a {@link List} of {@link GoalDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<GoalDTO> findByCriteria(GoalCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Goal> specification = createSpecification(criteria);
        return goalMapper.toDto(goalRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link GoalDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<GoalDTO> findByCriteria(GoalCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Goal> specification = createSpecification(criteria);
        return goalRepository.findAll(specification, page)
            .map(goalMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(GoalCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Goal> specification = createSpecification(criteria);
        return goalRepository.count(specification);
    }

    /**
     * Function to convert GoalCriteria to a {@link Specification}
     */
    private Specification<Goal> createSpecification(GoalCriteria criteria) {
        Specification<Goal> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Goal_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Goal_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Goal_.description));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Goal_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Goal_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Goal_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Goal_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Goal_.modifyDate));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(Goal_.educationalModules, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getRequestEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestEducationalModuleId(),
                    root -> root.join(Goal_.requestEducationalModules, JoinType.LEFT).get(RequestEducationalModule_.id)));
            }
        }
        return specification;
    }
}
