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

import com.marineindustryproj.domain.AssessmentMethod;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.AssessmentMethodRepository;
import com.marineindustryproj.service.dto.AssessmentMethodCriteria;
import com.marineindustryproj.service.dto.AssessmentMethodDTO;
import com.marineindustryproj.service.mapper.AssessmentMethodMapper;

/**
 * Service for executing complex queries for AssessmentMethod entities in the database.
 * The main input is a {@link AssessmentMethodCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AssessmentMethodDTO} or a {@link Page} of {@link AssessmentMethodDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AssessmentMethodQueryService extends QueryService<AssessmentMethod> {

    private final Logger log = LoggerFactory.getLogger(AssessmentMethodQueryService.class);

    private final AssessmentMethodRepository assessmentMethodRepository;

    private final AssessmentMethodMapper assessmentMethodMapper;

    public AssessmentMethodQueryService(AssessmentMethodRepository assessmentMethodRepository, AssessmentMethodMapper assessmentMethodMapper) {
        this.assessmentMethodRepository = assessmentMethodRepository;
        this.assessmentMethodMapper = assessmentMethodMapper;
    }

    /**
     * Return a {@link List} of {@link AssessmentMethodDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AssessmentMethodDTO> findByCriteria(AssessmentMethodCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AssessmentMethod> specification = createSpecification(criteria);
        return assessmentMethodMapper.toDto(assessmentMethodRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AssessmentMethodDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AssessmentMethodDTO> findByCriteria(AssessmentMethodCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AssessmentMethod> specification = createSpecification(criteria);
        return assessmentMethodRepository.findAll(specification, page)
            .map(assessmentMethodMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AssessmentMethodCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AssessmentMethod> specification = createSpecification(criteria);
        return assessmentMethodRepository.count(specification);
    }

    /**
     * Function to convert AssessmentMethodCriteria to a {@link Specification}
     */
    private Specification<AssessmentMethod> createSpecification(AssessmentMethodCriteria criteria) {
        Specification<AssessmentMethod> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), AssessmentMethod_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), AssessmentMethod_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), AssessmentMethod_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), AssessmentMethod_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), AssessmentMethod_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), AssessmentMethod_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), AssessmentMethod_.modifyDate));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(AssessmentMethod_.educationalModules, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getRequestEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestEducationalModuleId(),
                    root -> root.join(AssessmentMethod_.requestEducationalModules, JoinType.LEFT).get(RequestEducationalModule_.id)));
            }
        }
        return specification;
    }
}
