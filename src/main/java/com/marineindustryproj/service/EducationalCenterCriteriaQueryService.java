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

import com.marineindustryproj.domain.EducationalCenterCriteria;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EducationalCenterCriteriaRepository;
import com.marineindustryproj.service.dto.EducationalCenterCriteriaCriteria;
import com.marineindustryproj.service.dto.EducationalCenterCriteriaDTO;
import com.marineindustryproj.service.mapper.EducationalCenterCriteriaMapper;

/**
 * Service for executing complex queries for EducationalCenterCriteria entities in the database.
 * The main input is a {@link EducationalCenterCriteriaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EducationalCenterCriteriaDTO} or a {@link Page} of {@link EducationalCenterCriteriaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EducationalCenterCriteriaQueryService extends QueryService<EducationalCenterCriteria> {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterCriteriaQueryService.class);

    private final EducationalCenterCriteriaRepository educationalCenterCriteriaRepository;

    private final EducationalCenterCriteriaMapper educationalCenterCriteriaMapper;

    public EducationalCenterCriteriaQueryService(EducationalCenterCriteriaRepository educationalCenterCriteriaRepository, EducationalCenterCriteriaMapper educationalCenterCriteriaMapper) {
        this.educationalCenterCriteriaRepository = educationalCenterCriteriaRepository;
        this.educationalCenterCriteriaMapper = educationalCenterCriteriaMapper;
    }

    /**
     * Return a {@link List} of {@link EducationalCenterCriteriaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EducationalCenterCriteriaDTO> findByCriteria(EducationalCenterCriteriaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EducationalCenterCriteria> specification = createSpecification(criteria);
        return educationalCenterCriteriaMapper.toDto(educationalCenterCriteriaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EducationalCenterCriteriaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EducationalCenterCriteriaDTO> findByCriteria(EducationalCenterCriteriaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EducationalCenterCriteria> specification = createSpecification(criteria);
        return educationalCenterCriteriaRepository.findAll(specification, page)
            .map(educationalCenterCriteriaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EducationalCenterCriteriaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EducationalCenterCriteria> specification = createSpecification(criteria);
        return educationalCenterCriteriaRepository.count(specification);
    }

    /**
     * Function to convert EducationalCenterCriteriaCriteria to a {@link Specification}
     */
    private Specification<EducationalCenterCriteria> createSpecification(EducationalCenterCriteriaCriteria criteria) {
        Specification<EducationalCenterCriteria> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EducationalCenterCriteria_.id));
            }
            if (criteria.getGroup() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGroup(), EducationalCenterCriteria_.group));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), EducationalCenterCriteria_.title));
            }
            if (criteria.getDisplayOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDisplayOrder(), EducationalCenterCriteria_.displayOrder));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), EducationalCenterCriteria_.weight));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), EducationalCenterCriteria_.description));
            }
            if (criteria.getPeopleCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPeopleCount(), EducationalCenterCriteria_.peopleCount));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), EducationalCenterCriteria_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EducationalCenterCriteria_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EducationalCenterCriteria_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EducationalCenterCriteria_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EducationalCenterCriteria_.modifyDate));
            }
            if (criteria.getEducationalCenterGradeScoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalCenterGradeScoreId(),
                    root -> root.join(EducationalCenterCriteria_.educationalCenterGradeScores, JoinType.LEFT).get(EducationalCenterGradeScore_.id)));
            }
        }
        return specification;
    }
}
