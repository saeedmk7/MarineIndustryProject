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

import com.marineindustryproj.domain.EvaluationMethod;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EvaluationMethodRepository;
import com.marineindustryproj.service.dto.EvaluationMethodCriteria;
import com.marineindustryproj.service.dto.EvaluationMethodDTO;
import com.marineindustryproj.service.mapper.EvaluationMethodMapper;

/**
 * Service for executing complex queries for EvaluationMethod entities in the database.
 * The main input is a {@link EvaluationMethodCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EvaluationMethodDTO} or a {@link Page} of {@link EvaluationMethodDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EvaluationMethodQueryService extends QueryService<EvaluationMethod> {

    private final Logger log = LoggerFactory.getLogger(EvaluationMethodQueryService.class);

    private final EvaluationMethodRepository evaluationMethodRepository;

    private final EvaluationMethodMapper evaluationMethodMapper;

    public EvaluationMethodQueryService(EvaluationMethodRepository evaluationMethodRepository, EvaluationMethodMapper evaluationMethodMapper) {
        this.evaluationMethodRepository = evaluationMethodRepository;
        this.evaluationMethodMapper = evaluationMethodMapper;
    }

    /**
     * Return a {@link List} of {@link EvaluationMethodDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EvaluationMethodDTO> findByCriteria(EvaluationMethodCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EvaluationMethod> specification = createSpecification(criteria);
        return evaluationMethodMapper.toDto(evaluationMethodRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EvaluationMethodDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EvaluationMethodDTO> findByCriteria(EvaluationMethodCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EvaluationMethod> specification = createSpecification(criteria);
        return evaluationMethodRepository.findAll(specification, page)
            .map(evaluationMethodMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EvaluationMethodCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EvaluationMethod> specification = createSpecification(criteria);
        return evaluationMethodRepository.count(specification);
    }

    /**
     * Function to convert EvaluationMethodCriteria to a {@link Specification}
     */
    private Specification<EvaluationMethod> createSpecification(EvaluationMethodCriteria criteria) {
        Specification<EvaluationMethod> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EvaluationMethod_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), EvaluationMethod_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), EvaluationMethod_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EvaluationMethod_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EvaluationMethod_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EvaluationMethod_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EvaluationMethod_.modifyDate));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(EvaluationMethod_.educationalModules, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getRequestEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestEducationalModuleId(),
                    root -> root.join(EvaluationMethod_.requestEducationalModules, JoinType.LEFT).get(RequestEducationalModule_.id)));
            }
        }
        return specification;
    }
}
